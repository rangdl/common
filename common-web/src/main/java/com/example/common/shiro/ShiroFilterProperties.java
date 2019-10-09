package com.example.common.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ShiroFilterProperties
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/9 18:03
 * @Version 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "permission-config")
public class ShiroFilterProperties {
    List<Map<String, String>> perms;
}
