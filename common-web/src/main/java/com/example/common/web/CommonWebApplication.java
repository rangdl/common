package com.example.common.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author rdl
 * @Description 在SpringBoot的入口加上exclude= {DataSourceAutoConfiguration.class}（测试时）
 * @Date 2019/8/29 17:00
 * @Param
 * @return
 **/
@SpringBootApplication(scanBasePackages = {"com.example.common"})
@MapperScan({"com.example.common.mapper"})
@ServletComponentScan
@EnableCaching
public class CommonWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonWebApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
