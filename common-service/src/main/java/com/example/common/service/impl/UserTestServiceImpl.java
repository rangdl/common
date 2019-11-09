package com.example.common.service.impl;

import com.example.common.cache.CacheSpaceConfig;
import com.example.common.mapper.auth.UserMapper;
import com.example.common.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @ClassName IndexServiceImpl
 * @Description TODO
 * @Author rdl
 * @Date 2019/8/29 17:08
 * @Version 1.0
 **/
@Service
@CacheConfig(cacheNames = CacheSpaceConfig.CACHE_NAME_USER)
public class UserTestServiceImpl implements UserTestService {

    @Autowired
    UserMapper userMapper;
    @Cacheable(key = "#root.methodName+'['+#id+']'")
    @Override
    public String getUserById(int id) {
        return userMapper.selectById(id).toString();
    }
}
