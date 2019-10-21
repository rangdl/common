package com.example.common.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.common.pojo.constant.Constants;
import com.example.common.pojo.entity.auth.User;
import com.example.common.pojo.security.LoginUser;
import com.example.common.pojo.security.UserContext;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
//    @CrossOrigin(origins = "http://localhost:8081")
    @ResponseBody
    @RequestMapping(value="/login",method = {RequestMethod.POST,RequestMethod.GET})
    public ResultVo login(HttpServletResponse response, @RequestBody UserVo user) {
        return userService.login(user,response);
    }

    /**
     * erp登录验证ticket，生成本地token，由本地来管理token生命周期
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/valid_erp",method = {RequestMethod.POST,RequestMethod.GET})
    public ResultVo loginErp(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", domain);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return userService.loginErp(response);
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


//        User user;
//        user = userService.findUserByAccount(JwtUtils.getClaim(SecurityUtils.getSubject().getPrincipal().toString(), SecurityConsts.ACCOUNT));

        json.put("name", currentUser.getName());
//        json.put("erp", user.getErpFlag());

        json.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        json.put("roles",new String[]{"admin"});

        //查询菜单
//        List<ResourceNode> menus = resourceService.findByUserId(user.getId());

        //查询权限
        List<Object> authorityList = authorityService.findByUserId(currentUser.getUserId());

//        json.put("menus",menus);
        json.put("auth",authorityList);

        result.setData(json);
        return result;
    }
}
