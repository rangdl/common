package com.example;

import com.example.common.shiro.security.JwtProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author rdl
 * @Description 在SpringBoot的入口加上exclude= {DataSourceAutoConfiguration.class}（测试时）
 * @Date 2019/8/29 17:00
 * @Param
 * @return
 **/
@EnableConfigurationProperties({JwtProperties.class})
@SpringBootApplication(scanBasePackages = {"com.example"})
@MapperScan({"com.example.dao"})
@ServletComponentScan
@EnableCaching
public class CommonWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonWebApplication.class, args);
    }

}
