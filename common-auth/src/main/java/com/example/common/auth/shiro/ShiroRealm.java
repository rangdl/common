package com.example.common.auth.shiro;

import com.example.common.pojo.entity.auth.Role;
import com.example.common.pojo.entity.auth.User;
import com.example.common.utils.security.pojo.JwtToken;
import com.example.common.service.auth.AuthorityService;
import com.example.common.service.auth.RoleService;
import com.example.common.service.auth.UserService;
import com.example.common.utils.security.JwtUtils;
import com.example.common.utils.security.SecurityConsts;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ShiroRealm
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/11 10:18
 * @Version 1.0
 **/
@Service
public class ShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    private RoleService roleService;

    @Autowired
    @Lazy
    private AuthorityService authorityService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth)
            throws AuthenticationException {
        String token = (String)auth.getPrincipal();
        String account  = JwtUtils.getClaim(token, SecurityConsts.ACCOUNT);

        if (account == null) {
            throw new AuthenticationException("token invalid");
        }

        if (JwtUtils.verify(token)) {
            return new SimpleAuthenticationInfo(token, token, "shiroRealm");
        }
        throw new AuthenticationException("Token expired or incorrect.");
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		logger.info("调用doGetAuthorizationInfo方法获取权限");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        String account = JwtUtils.getClaim(principals.toString(), SecurityConsts.ACCOUNT);
        User UserInfo = userService.findUserByAccount(account);

        //获取role
        List<Role> RoleList = roleService.findRoleByUserId(UserInfo.getId());
        //获取权限
        List<Object> AuthorityList = authorityService.findByUserId(UserInfo.getId());
        for(Role Role : RoleList){
            authorizationInfo.addRole(Role.getName());
            for(Object auth: AuthorityList){
                authorizationInfo.addStringPermission(auth.toString());
            }
        }
        return authorizationInfo;
    }
}
