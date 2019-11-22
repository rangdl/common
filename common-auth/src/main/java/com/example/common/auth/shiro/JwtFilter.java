package com.example.common.auth.shiro;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.common.pojo.constant.enumtype.ResultEnum;
import com.example.common.utils.security.pojo.JwtToken;
import com.example.common.utils.security.pojo.LoginUser;
import com.example.common.utils.security.pojo.UserContext;
import com.example.common.pojo.vo.ResultVo;
import com.example.common.service.auth.UserService;
import com.example.common.utils.security.JwtProperties;
import com.example.common.utils.security.JwtUtils;
import com.example.common.utils.security.SecurityConsts;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * @ClassName JwtFilter
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/11 10:21
 * @Version 1.0
 **/

public class JwtFilter extends BasicHttpAuthenticationFilter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    JwtProperties jwtProperties;
//    ISyncCacheService syncCacheService;
//    JedisUtils jedisUtils;

//    public JwtFilter(JwtProperties jwtProperties, ISyncCacheService syncCacheService, JedisUtils jedisUtils){
//        this.jwtProperties=jwtProperties;
//        this.syncCacheService=syncCacheService;
//        this.jedisUtils = jedisUtils;
//    }
    public JwtFilter(JwtProperties jwtProperties,UserService userService){
        this.jwtProperties=jwtProperties;
        this.userService=userService;
    }
    /**
     * @Author rdl
     * @Description 获取请求头中的token,如果请求头token为空 则在请求参数里获取token
     * @Date 2019/11/15 8:42
     * @Param [request]
     * @return java.lang.String
     **/
    public String getToken(ServletRequest request){
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(SecurityConsts.REQUEST_AUTH_HEADER);
        if (Objects.isNull(authorization))authorization = String.valueOf(req.getParameter(SecurityConsts.REQUEST_AUTH_HEADER));
        return authorization;
    }


    /**
     * 检测Header里token字段
     * 判断是否登录
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        return !Objects.isNull(getToken(request));
    }

    /**
     * 登录验证
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response){
        logger.info("调用executeLogin验证登录");
        String authorization = getToken(request);
        JwtToken token = new JwtToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);

        //绑定上下文获取账号
        String account = JwtUtils.getClaim(authorization, SecurityConsts.ACCOUNT);
        String name = JwtUtils.getClaim(authorization, SecurityConsts.USER_NAME);
        Long id = JwtUtils.getClaimId(authorization,  SecurityConsts.USER_ID);
        Long key = JwtUtils.getClaimId(authorization,  SecurityConsts.TOKEN_KEY);
        LoginUser loginUser = new LoginUser(id, account, name,key);

        //绑定上下文
        new UserContext(loginUser);

        //检测token是否失效 -登陆成功后用户表存储 token_key(时间戳) 退出登录或者主动使token 失效时更新
        if (userService.checkTokenKey(id,key)){
            return false;
        }

        //检查是否需要更换token，需要则重新颁发
        this.refreshTokenIfNeed(loginUser, authorization, response);

        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 检查是否需要,若需要则校验时间戳，刷新Token，并更新时间戳
     * @param loginUser
     * @param authorization
     * @param response
     * @return
     */
    private boolean refreshTokenIfNeed(LoginUser loginUser, String authorization, ServletResponse response) {
        Long currentTimeMillis = System.currentTimeMillis();
        //检查刷新规则
        if (this.refreshCheck(authorization, currentTimeMillis)) {
//            String lockName = SecurityConsts.PREFIX_SHIRO_REFRESH_CHECK + account;
//            boolean b = syncCacheService.getLock(lockName, Constants.ExpireTime.ONE_MINUTE);
//            if (b) {
//                //获取到锁
//                String refreshTokenKey= SecurityConsts.PREFIX_SHIRO_REFRESH_TOKEN + account;
//                if(jedisUtils.exists(refreshTokenKey)){
//                    //检查redis中的时间戳与token的时间戳是否一致
//                    String tokenTimeStamp = jedisUtils.get(refreshTokenKey);
//                    String tokenMillis= JwtUtils.getClaim(authorization,SecurityConsts.CURRENT_TIME_MILLIS);
//                    if(!tokenMillis.equals(tokenTimeStamp)){
//                        throw new TokenExpiredException(String.format("账户%s的令牌无效", account));
//                    }
//                }
//                //时间戳一致，则颁发新的令牌
//                logger.info(String.format("为账户%s颁发新的令牌", account));
//                String strCurrentTimeMillis = String.valueOf(currentTimeMillis);
//                String newToken = JwtUtils.sign(account,strCurrentTimeMillis);
//
//                //更新缓存中的token时间戳
//                jedisUtils.saveString(refreshTokenKey, strCurrentTimeMillis, jwtProperties.getTokenExpireTime()*60);
//
//                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//                httpServletResponse.setHeader(SecurityConsts.REQUEST_AUTH_HEADER, newToken);
//                httpServletResponse.setHeader("Access-Control-Expose-Headers", SecurityConsts.REQUEST_AUTH_HEADER);
//            }
//            syncCacheService.releaseLock(lockName);
            //时间戳一致，则颁发新的令牌
            logger.info(String.format("为账户%s颁发新的令牌", loginUser.getAccount()));
            String strCurrentTimeMillis = String.valueOf(currentTimeMillis);
            String newToken = JwtUtils.sign(loginUser,strCurrentTimeMillis);
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setHeader(SecurityConsts.REQUEST_AUTH_HEADER, newToken);
            httpServletResponse.setHeader("Access-Control-Expose-Headers", SecurityConsts.REQUEST_AUTH_HEADER);
        }
        return true;
    }

    /**
     * 检查是否需要更新Token
     *
     * @param authorization
     * @param currentTimeMillis
     * @return
     */
    private boolean refreshCheck(String authorization, Long currentTimeMillis) {
        String tokenMillis = JwtUtils.getClaim(authorization, SecurityConsts.CURRENT_TIME_MILLIS);
        if (currentTimeMillis - Long.parseLong(tokenMillis) > (jwtProperties.getRefreshCheckTime() * 60 * 1000L)) {
            return true;
        }
        return false;
    }

    /**
     * 是否允许访问
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                if (!this.executeLogin(request, response)){
                    this.response401(response, "Token已过期!");
                    return false;
                }
                return true;
            } catch (Exception e) {
                String msg = e.getMessage();
                Throwable throwable = e.getCause();
                if (throwable != null && throwable instanceof SignatureVerificationException) {
                    msg = String.format("Token或者密钥不正确(%s)",throwable.getMessage());
                } else if (throwable != null && throwable instanceof TokenExpiredException) {
                    msg = String.format("Token已过期(%s)",throwable.getMessage());
                } else {
                    if (throwable != null) {
                        msg = throwable.getMessage();
                    }
                }
                this.response401(response, msg);
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * 重写 onAccessDenied 方法，避免父类中调用再次executeLogin
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
//        logger.info("调用onAccessDenied拒绝访问");
        this.sendChallenge(request, response);
        return false;
    }

    /**
     * 401非法请求
     * @param resp
     * @param msg
     */
    private void response401(ServletResponse resp, String msg) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = httpServletResponse.getWriter();

            ResultVo result = ResultVo.getResultVo(ResultEnum._401);
            result.setMessage(msg);

            out.append(JSON.toJSONString(result));
        } catch (IOException e) {
            logger.error("返回Response信息出现IOException异常:" + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
