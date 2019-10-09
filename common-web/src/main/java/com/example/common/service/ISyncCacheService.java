package com.example.common.service;

/**
 * @author rdl
 * @version V1.0
 * @ClassName ISyncCacheService
 * @Description TODO
 * @date 2019/10/9 17:56
 **/
public interface ISyncCacheService {
    Boolean getLock(String lockName, int expireTime);

    Boolean releaseLock(String lockName);
}
