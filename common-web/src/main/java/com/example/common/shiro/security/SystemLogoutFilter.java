package com.example.common.shiro.security;

import com.alibaba.fastjson.JSON;
import com.example.common.shiro.SecurityConsts;
import com.example.common.constant.Constants;
import com.example.common.utils.JedisUtils;
import com.example.common.sysmgr.vo.Result;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName SystemLogoutFilter
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/9 15:13
 * @Version 1.0
 **/
public class SystemLogoutFilter extends LogoutFilter {
    JedisUtils jedisUtils;

    public SystemLogoutFilter() {
    }

    public SystemLogoutFilter(JedisUtils jedisUtils) {
        this.jedisUtils = jedisUtils;
    }

    private static final Logger logger = LoggerFactory.getLogger(SystemLogoutFilter.class);

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String authorization = httpServletRequest.getHeader(SecurityConsts.REQUEST_AUTH_HEADER);
            String account = JwtUtil.getClaim(authorization, SecurityConsts.ACCOUNT);

            if(!StringUtils.isEmpty(account)){
                // 清除可能存在的Shiro权限信息缓存
                String tokenKey = SecurityConsts.PREFIX_SHIRO_CACHE + account;
                if (jedisUtils.exists(tokenKey)) {
                    jedisUtils.delKey(tokenKey);
                }
            }

            subject.logout();
        } catch (Exception ex) {
            logger.error("退出登录错误",ex);
        }

        this.writeResult(response);
        //不执行后续的过滤器
        return false;
    }

    private void writeResult(ServletResponse response){
        //响应Json结果
        PrintWriter out = null;
        try {
            out = response.getWriter();
            Result result = new Result(true,null,null, Constants.TOKEN_CHECK_SUCCESS);
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
