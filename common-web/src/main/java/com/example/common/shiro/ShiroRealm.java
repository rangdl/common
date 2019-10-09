package com.example.common.shiro;

import com.example.common.shiro.security.JwtToken;
import com.example.common.shiro.security.JwtUtil;
import com.example.common.sysmgr.entity.Role;
import com.example.common.sysmgr.entity.User;
import com.example.common.sysmgr.service.AuthorityService;
import com.example.common.sysmgr.service.RoleService;
import com.example.common.sysmgr.service.UserService;
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
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ShiroRealm
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/9 14:38
 * @Version 1.0
 **/
@Service
public class ShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
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
        String account  = JwtUtil.getClaim(token, SecurityConsts.ACCOUNT);

        if (account == null) {
            throw new AuthenticationException("token invalid");
        }

        if (JwtUtil.verify(token)) {
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

        String account = JwtUtil.getClaim(principals.toString(), SecurityConsts.ACCOUNT);
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
