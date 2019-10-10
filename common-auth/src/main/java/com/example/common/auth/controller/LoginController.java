package com.example.common.auth.controller;

import com.example.common.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/10 18:36
 * @Version 1.0
 **/
@Controller
public class LoginController {
    @Autowired
    UserTestService userTestService;

    @ResponseBody
    @RequestMapping("login")
    public String hello(Integer id){
        String str = "Login!";
//        if (Objects.isNull(id)) id=1;
//        String user1 = userTestService.getUserById(id);
//        str+=user1;
        return str;
    }
}
