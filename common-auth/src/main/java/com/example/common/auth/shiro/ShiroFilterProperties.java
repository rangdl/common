package com.example.common.auth.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ShiroFilterProperties
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/11 10:17
 * @Version 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "permission-config")
public class ShiroFilterProperties {
    List<Map<String, String>> perms;
}
