package com.example.common.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.common.cache.aspect.CacheUtils;
import com.example.common.pojo.constant.Constants;
import com.example.common.pojo.entity.auth.User;
import com.example.common.utils.security.pojo.LoginUser;
import com.example.common.utils.security.pojo.UserContext;
import com.example.common.pojo.vo.ResultVo;
import com.example.common.pojo.vo.auth.UserVo;
import com.example.common.service.auth.AuthorityService;
import com.example.common.service.auth.UserService;
import com.example.common.utils.security.JwtUtils;
import com.example.common.utils.security.SecurityConsts;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/10 18:36
 * @Version 1.0
 **/
@Controller
@RequestMapping(value="/login")
public class LoginController {
    @Autowired
    UserService userService;

//    @Autowired
//    ResourceService resourceService;

    @Autowired
    AuthorityService authorityService;

    @Value("${project.domain}")
    String domain;
    /**
     * test
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/test")
    public String test() {
        return "test123";
    }
    /**
     * 登录
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/login",method = {RequestMethod.POST})
    public ResultVo login(HttpServletResponse response, @RequestBody UserVo user) {
        return userService.login(user,response);
    }
    /**
     * 登出 刷新user的 tokenKey 以此使token失效
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/logout",method = {RequestMethod.POST})
    public ResultVo logout() {
        return userService.logout(UserContext.getCurrentUser().getUserId());
    }

    /**
     * erp登录验证ticket，生成本地token，由本地来管理token生命周期
     * @return
     */
    @ResponseBody
//    @RequestMapping(value="/valid_erp",method = {RequestMethod.POST,RequestMethod.GET})
    public ResultVo loginErp(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", domain);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return userService.loginErp(response);
    }

    /**
     * 返回ehcache缓存使用状况
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/cache",method = {RequestMethod.POST,RequestMethod.GET})
    public ResultVo cache() {
        return ResultVo.getSuccess(CacheUtils.getCacheStatusMap());
    }

    /**
     * 获取登录用户基础信息
     * @return
     */
    @RequiresAuthentication
    @ResponseBody
    @RequestMapping(value="/info",method = {RequestMethod.POST,RequestMethod.GET})
    public ResultVo info(){
        LoginUser currentUser = UserContext.getCurrentUser();
        ResultVo result = new ResultVo();
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        JSONObject json = new JSONObject();

        User user = userService.findUserByAccount(JwtUtils.getClaim(SecurityUtils.getSubject().getPrincipal().toString(), SecurityConsts.ACCOUNT));
        json.put(SecurityConsts.USER_NAME,user.getName());
        json.put(SecurityConsts.ACCOUNT,user.getAccount());
//        json.put("erp", user.getFlagErp());
        if (Objects.isNull(user.getAvatar())) user.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        json.put("avatar",user.getAvatar());
        json.put("roles",new String[]{"admin"});

        //查询菜单
//        List<ResourceNode> menus = resourceService.findByUserId(user.getId());

        //查询权限
        List<Object> authorityList = authorityService.findByUserId(user.getId());

//        json.put("menus",menus);
        json.put("auth",authorityList);

        result.setData(json);
        return result;
    }
}
