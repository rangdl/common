package com.example.common.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RedisKeys
 * @Description TODO
 * @Author rdl
 * @Date 2019/8/31 11:43
 * @Version 1.0
 **/
public class CacheSpaceConfig  {
    // 测试 begin
    //用户登陆后 权限缓存空间
    public static final String CACHE_NAME = "cache";// 缓存空间名称前缀
    public static final String CACHE_NAME_AUTH = "cache-auth";// 缓存空间名
    public static final Long CACHE_NAME_AUTH_SECOND = 1800L;// 缓存时间


    public static final String CACHE_NAME_USER = "cache-user";// 缓存空间名
    public static final Long CACHE_NAME_USER_SECOND = 1800L;// 缓存时间


    // 测试 end

    // 根据key设定具体的缓存时间
    private static Map<String, Long> expiresMap = new HashMap<>();

    static {
        expiresMap.put(CACHE_NAME_USER,CACHE_NAME_USER_SECOND);
    }

    public static Map<String, Long> getExpiresMap(){
        return expiresMap;
    }
}
