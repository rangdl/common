package com.example.web;

import com.example.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author rdl
 * @Date 2019/8/29 16:59
 * @Version 1.0
 **/
@Controller
public class IndexController {

    @Autowired
    UserTestService userTestService;

    @ResponseBody
    @RequestMapping("hello")
    public String hello(){
        String str = "Hello SpringBoot!";
        str+=userTestService.getUserById(1);
        return str;
    }
}
