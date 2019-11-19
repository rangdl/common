package com.example.common.cache.aspect;

import net.sf.ehcache.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CacheUtils
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/18 14:19
 * @Version 1.0
 **/
@Component
public class CacheUtils {

//    private static CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);
    @Autowired
    private CacheManager manager;
    private static CacheManager cacheManager;
    @PostConstruct
    public void init(){
        CacheUtils.cacheManager = manager;
        String[] cacheNames = cacheManager.getCacheNames();
        for (String cacheName : cacheNames) {
            Cache cache = cacheManager.getCache(cacheName);
            cache.setStatisticsEnabled(true);
        }
    }
    public static Object get(String cacheName, String key) {
        Element element = getCache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }

    public static List<Map<String, Object>> getCacheStatusMap() {
        List<Map<String, Object>> list = new ArrayList<>();
        String[] cacheNames = cacheManager.getCacheNames();

        for (String cacheName : cacheNames) {
            Map<String, Object> map = new HashMap<>();
            Cache cache = cacheManager.getCache(cacheName);
//            cache.setStatisticsEnabled(true);
            Statistics cacheStatus = cache.getStatistics();

            map.put("cacheName", cacheName);

            map.put("num", cache.getSize());
            map.put("numName", "缓存数量");

            map.put("cacheHits", cacheStatus.getCacheHits());
            map.put("cacheHitsName", "缓存命中数");

            map.put("misses", cacheStatus.getCacheMisses());
            map.put("missesName", "未命中数");
            list.add(map);
        }

        return list;
    }


    /**
     * @return void
     * @Author rdl
     * @Description 手动填入一条缓存
     * @Date 2019/10/20 9:33
     * @Param [cacheName, key, value] 缓存空间名,key值,
     **/
    public static void put(String cacheName, String key, Object value) {
        Element element = new Element(key, value);
        getCache(cacheName).put(element);
    }

    /**
     * @return boolean
     * @Author rdl
     * @Description 移除一个对应的key缓存
     * @Date 2019/10/20 9:34
     * @Param [cacheName, key]
     **/
    public static boolean remove(String cacheName, String key) {
        return getCache(cacheName).remove(key);
    }

    /**
     * @return void
     * @Author rdl
     * @Description 移除缓存空间下的所有缓存
     * @Date 2019/10/20 9:34
     * @Param [cacheName, key]
     **/
    public static void removeAll(String cacheName) {
        getCache(cacheName).removeAll();
    }

    /**
     * @return java.util.List
     * @Author rdl
     * @Description 获取缓存空间下的所有缓存
     * @Date 2019/10/20 9:34
     * @Param [cacheName]
     **/
    public static List cacheKeys(String cacheName) {
        return getCache(cacheName).getKeys();
    }

    /**
     * 获得一个Cache，没有则创建一个。
     *
     * @param cacheName
     * @return
     */
    private static Cache getCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            cacheManager.addCache(cacheName);
            cache = cacheManager.getCache(cacheName);
            cache.getCacheConfiguration().setEternal(true);
        }
        return cache;
    }

    public static CacheManager getCacheManager() {
        return cacheManager;
    }
}
