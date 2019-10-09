package com.example.service.impl;

        import com.example.cache.redis.CacheSpaceConfig;
        import com.example.service.UserTestService;
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
//
//    @Autowired
//    UserMapper userMapper;

    @Cacheable(key = "#root.methodName+'['+#id+']'")
    @Override
    public String getUserById(int id) {
//        String userName = userMapper.selectUserName(id);
        String userName = "Hello!";
        return userName;
    }
}
