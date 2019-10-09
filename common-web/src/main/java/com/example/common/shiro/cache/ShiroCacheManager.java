package com.example.common.shiro.cache;

import com.example.common.shiro.security.JwtProperties;
import com.example.common.utils.JedisUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName ShiroCacheManager
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/9 14:53
 * @Version 1.0
 **/
public class ShiroCacheManager implements CacheManager {
    @Autowired
    JedisUtils jedisUtils;

    @Autowired
    JwtProperties jwtProperties;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new ShiroCache<K,V>(jedisUtils,jwtProperties);
    }
}
