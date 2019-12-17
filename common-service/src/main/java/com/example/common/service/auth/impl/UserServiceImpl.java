package com.example.common.service.auth.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.cache.CacheSpaceConfig;
import com.example.common.cache.aspect.CacheRemove;
import com.example.common.mapper.auth.UserMapper;
import com.example.common.pojo.constant.Constants;
import com.example.common.pojo.constant.enumtype.Enums;
import com.example.common.pojo.constant.enumtype.ResultEnum;
import com.example.common.utils.security.pojo.LoginUser;
import com.example.common.utils.security.JwtProperties;
import com.example.common.utils.security.JwtUtils;
import com.example.common.utils.security.SecurityConsts;
import com.example.common.pojo.entity.auth.LoginLog;
import com.example.common.pojo.entity.auth.User;
import com.example.common.pojo.entity.auth.UserRole;
import com.example.common.utils.security.pojo.JwtToken;
import com.example.common.utils.security.pojo.UserContext;
import com.example.common.pojo.vo.ResultVo;
import com.example.common.pojo.vo.auth.UserPassword;
import com.example.common.pojo.vo.auth.UserRoleVo;
import com.example.common.pojo.vo.auth.UserVo;
import com.example.common.service.auth.LoginLogService;
import com.example.common.service.auth.UserService;
import com.example.common.utils.security.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.*;

@Service
@CacheConfig(cacheNames = CacheSpaceConfig.CACHE_NAME_USER)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    JwtProperties jwtProperties;

//    @Autowired
//    JedisUtils jedisUtils;

    @Autowired
    LoginLogService loginLogService;

    @Override
    public User findUserByAccount(String account) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account", account);
        wrapper.eq("flag", Constants.VALID);

        List<User> userList = baseMapper.selectList(wrapper);
        return userList.size() > 0 ? userList.get(0) : null;
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public ResultVo login(UserVo user, HttpServletResponse response) {

        if (Objects.isNull(user.getUsername()))
//            return ResultVo.getResultVo(ResultEnum.E);
            if (Objects.isNull(user.getPassword()))
                return ResultVo.getResultVo(ResultEnum._200_LOGIN);
//        Assert.notNull(user.getUsername(), "用户名不能为空");
//        Assert.notNull(user.getPassword(), "密码不能为空");

        User userBean = this.findUserByAccount(user.getUsername());

        if (userBean == null) {
            return ResultVo.getResultVo(ResultEnum._400_NOT_EXIST_ACCOUNT);
        }

        //ERP账号直接提示账号不存在
        if (userBean.getFlagErp()) {
            return ResultVo.getResultVo(ResultEnum._400_NOT_EXIST_ACCOUNT);
        }

        String encodePassword = ShiroUtils.md5(user.getPassword(), SecurityConsts.LOGIN_SALT);
        if (!encodePassword.equals(userBean.getPwd())) {
            return ResultVo.getResultVo(ResultEnum._400_PASSWORD);
        }

        //账号是否锁定
        if (!Objects.isNull(userBean.getState()) && userBean.getState().equals(0)) {
            return ResultVo.getResultVo(ResultEnum._400_LOCKING);
        }
        String strToken = this.loginSuccess(new LoginUser(userBean.getId(), userBean.getAccount(), userBean.getName(), userBean.getTokenKey()), response);

        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new JwtToken(strToken);
        subject.login(token);
        //登录成功
        return ResultVo.getResultVo(ResultEnum._200_LOGIN);
    }

    /**
     * ERP登录
     *
     * @return
     */
    @Override
    public ResultVo loginErp(HttpServletResponse response) {

        //@Todo 待开发
//        User userBean = this.findUserByAccount("admin");
//        if (userBean == null || "0".equals(userBean.getErpFlag())) {
//            //ERP账号不在系统中，或者系统中标志是非ERP账号
//            return new Result(false, "用户未授权", null, Constants.PASSWORD_CHECK_INVALID);
//        }
//        //账号是否锁定
//        if ("0".equals(userBean.getStatus())) {
//            return new Result(false, "该账号已被锁定", null, Constants.PASSWORD_CHECK_INVALID);
//        }
        return ResultVo.getSuccess();
    }

    /**
     * 登录后更新缓存，生成token，设置响应头部信息
     *
     * @param loginUser
     * @param response
     */
    private String loginSuccess(LoginUser loginUser, HttpServletResponse response) {

        String currentTimeMillis = String.valueOf(System.currentTimeMillis());

        //生成token
        JSONObject json = new JSONObject();
        String token = JwtUtils.sign(loginUser, currentTimeMillis);
        json.put(SecurityConsts.REQUEST_AUTH_HEADER, token);

        //更新RefreshToken缓存的时间戳
//        String refreshTokenKey= SecurityConsts.PREFIX_SHIRO_REFRESH_TOKEN + account;
//        jedisUtils.saveString(refreshTokenKey, currentTimeMillis, jwtProperties.getTokenExpireTime()*60);

        //记录登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setAccount(loginUser.getAccount());
        loginLog.setLoginTime(Date.from(Instant.now()));
        loginLog.setContent(ResultEnum._200_LOGIN.getDescribe());
        loginLog.setFlag(Constants.VALID);
        loginLog.setCreator(loginUser.getUserId());
        loginLog.setEditor(loginUser.getUserId());
        loginLog.setCreatedTime(loginLog.getLoginTime());
        loginLogService.save(loginLog);

        //写入header
        response.setHeader(SecurityConsts.REQUEST_AUTH_HEADER, token);
        response.setHeader(SecurityConsts.REQUEST_AUTH_HEADER_CROSS, SecurityConsts.REQUEST_AUTH_HEADER);

        return token;
    }

    /**
     * 保存
     *
     * @param user
     * @return
     */
    @Override
    public ResultVo persist(User user) {

        Date currentDate = Date.from(Instant.now());
        if (user.getId() == null) {
            User existUser = this.findUserByAccount(user.getAccount());
            if (existUser != null) {
                //账号已存在
                return ResultVo.getResultVo(ResultEnum._400_EXIST_USER);
            } else {
                //保存密码
                if (!StringUtils.isEmpty(user.getPwd())) {
                    user.setPwd(ShiroUtils.md5(user.getPwd(), SecurityConsts.LOGIN_SALT));
                }
                user.setLastPwdModifiedTime(Date.from(Instant.now()));
//                user.setStatus(UserStatusEnum.NORMAL.code());
                user.setFlag(Constants.VALID);
                user.setEditor(UserContext.getCurrentUser().getUserId());
                user.setCreator(UserContext.getCurrentUser().getUserId());
                user.setCreatedTime(currentDate);
                user.setModifiedTime(currentDate);
                //新增用户
                baseMapper.insert(user);
            }
        } else {
            User userBean = baseMapper.selectById(user.getId());
            if (user.getAccount().equals(userBean.getAccount())) {
                if (!user.getPwd().equals("******")) {
                    //修改密码
                    user.setPwd(ShiroUtils.md5(user.getPwd(), SecurityConsts.LOGIN_SALT));
                    user.setLastPwdModifiedTime(Date.from(Instant.now()));
                } else {
                    user.setPwd(userBean.getPwd());
                    user.setLastPwdModifiedTime(userBean.getLastPwdModifiedTime());
                }
                user.setEditor(UserContext.getCurrentUser().getUserId());
                user.setModifiedTime(currentDate);
                //更新用户
                baseMapper.updateById(user);
            } else {
                return ResultVo.getResultVo(ResultEnum._400_NOT_ALLOW_MODIFY);
            }
        }

        return ResultVo.getSuccess(ResultEnum._200_MODIFY);
    }

    @Override
    public ResultVo findUserRole(Long userId) {
        List<Long> auths = baseMapper.selectRoleByUserId(userId);
        return ResultVo.getSuccess(auths);
    }

    /**
     * 保存用户角色
     *
     * @param userRole
     * @return
     */
    @Override
    public ResultVo saveUserRoles(UserRoleVo userRole) {
        Date currentDate = Date.from(Instant.now());

        UserRole user = new UserRole();
        user.setUserId(userRole.getUserId());
        user.setModifiedTime(currentDate);
        baseMapper.deleteRoleByUserId(user);

        UserRole tempUserRole;
        List<UserRole> authList = new ArrayList<>();
        for (Long roleId : userRole.getRoleIds()) {
            tempUserRole = new UserRole(userRole.getUserId(), roleId);
            tempUserRole.setFlag(Constants.VALID);
            tempUserRole.setEditor(UserContext.getCurrentUser().getUserId());
            tempUserRole.setCreator(UserContext.getCurrentUser().getUserId());
            tempUserRole.setCreatedTime(currentDate);
            tempUserRole.setModifiedTime(currentDate);
            authList.add(tempUserRole);
        }
        baseMapper.batchInsertUserRole(authList);

        return ResultVo.getSuccess(ResultEnum._200_SAVE);
    }

    /**
     * 修改密码
     *
     * @param userPassword
     * @return
     */
    @Override
    public ResultVo editPassWord(UserPassword userPassword) {
        if (!StringUtils.isEmpty(userPassword.getPassword()) && !StringUtils.isEmpty(userPassword.getNewPassword())) {

            User user = this.findUserByAccount(UserContext.getCurrentUser().getAccount());

            String encodeNewPassword = ShiroUtils.md5(userPassword.getPassword(), SecurityConsts.LOGIN_SALT);
            if (user.getFlagErp()) {
                if (user.getPwd().equals(encodeNewPassword)) {
                    User entity = new User();
                    entity.setPwd(ShiroUtils.md5(userPassword.getNewPassword(), SecurityConsts.LOGIN_SALT));
                    entity.setEditor(UserContext.getCurrentUser().getUserId());

                    entity.setEditor(UserContext.getCurrentUser().getUserId());
                    entity.setModifiedTime(Date.from(Instant.now()));

                    QueryWrapper<User> wrapper = new QueryWrapper<>();
                    wrapper.eq("flag", Constants.VALID);
                    wrapper.eq("account", user.getAccount());

                    baseMapper.update(entity, wrapper);

                    return ResultVo.getSuccess(ResultEnum._200_MODIFY);
                } else {
                    //原始密码错误
                    ResultVo.getSuccess(ResultEnum._400_PASSWORD_PRIMARY);
                }
            }
        }
        return ResultVo.getSuccess(ResultEnum._400_PARAMETERS_MISSING);
    }

    @Override
    @Cacheable(key = "'getTokenKey_'+#id")
    public long getTokenKey(Long id) {
        User user = baseMapper.selectById(id);
        return user.getTokenKey();
    }

    @Override
    @CacheRemove(value = CacheSpaceConfig.CACHE_NAME_USER, key = "'getTokenKey_'+#id")
    public ResultVo logout(Long id) {
        int i = baseMapper.updateTokenKeyById(id);
        if (i > 0) return ResultVo.getSuccess();
        else return ResultVo.getResultVo(ResultEnum._500);
    }
}
