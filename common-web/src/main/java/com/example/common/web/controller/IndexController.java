package com.example.common.web.controller;

import com.example.common.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

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
    public String hello(Integer id){
        String str = "Hello SpringBoot!12";
        if (Objects.isNull(id)) id=1;
        String user1 = userTestService.getUserById(id);
        str+=user1;
        return str;
    }
}
