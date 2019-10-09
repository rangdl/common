package com.example.service.impl;

        import com.example.cache.redis.CacheSpaceConfig;
        import com.example.dao.UserMapper;
        import com.example.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Cacheable(key = "#root.methodName+'['+#id+']'")
    @Override
    public String getUserById(int id) {
        String userName = userMapper.selectUserName(id);
        return userName;
    }
}
