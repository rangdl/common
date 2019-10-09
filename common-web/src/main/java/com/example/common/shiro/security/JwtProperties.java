package com.example.common.shiro.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName JwtProperties
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/9 14:42
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = "common")
@Data
public class JwtProperties {

    /**
     * token过期时间，单位分钟
     */
    Integer tokenExpireTime;

    /**
     * 更新令牌时间，单位分钟
     */
    Integer refreshCheckTime;

    /**
     * Shiro缓存有效期，单位分钟
     */
    Integer shiroCacheExpireTime;

    /**
     * token加密密钥
     */
    String secretKey;
}
