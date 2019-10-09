package com.example.common.service.impl;

import com.example.common.constant.Constants;
import com.example.common.service.ISyncCacheService;
import com.example.common.shiro.security.JwtProperties;
import com.example.common.utils.JedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ISyncCacheServiceImpl
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/9 17:56
 * @Version 1.0
 **/
@Service
public class SyncCacheServiceImpl implements ISyncCacheService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SyncCacheServiceImpl.class);

    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    JedisUtils jedisUtils;

    /**
     * 获取redis中key的锁，乐观锁实现
     * @param lockName
     * @param expireTime 锁的失效时间
     * @return
     */
    @Override
    public Boolean getLock(String lockName, int expireTime) {
        Boolean result = Boolean.FALSE;
        try {
            boolean isExist = jedisUtils.exists(lockName);
            if(!isExist){
                jedisUtils.getSeqNext(lockName,0);
                jedisUtils.expire(lockName,expireTime<=0? Constants.ExpireTime.ONE_HOUR:expireTime);
            }
            long reVal =  jedisUtils.getSeqNext(lockName,1);
            if(1l==reVal){
                //获取锁
                result = Boolean.TRUE;
                LOGGER.info("获取redis锁:"+lockName+",成功");
            }else {
                LOGGER.info("获取redis锁:"+lockName+",失败"+reVal);
            }
        } catch (Exception e) {
            LOGGER.error("获取redis锁失败:"+lockName, e);
        }
        return result;
    }

    /**
     * 释放锁，直接删除key(直接删除会导致任务重复执行，所以释放锁机制设为超时30s)
     * @param lockName
     * @return
     */
    @Override
    public Boolean releaseLock(String lockName) {
        Boolean result = Boolean.FALSE;
        try {
            jedisUtils.expire(lockName, Constants.ExpireTime.TEN_SEC);
            LOGGER.info("释放redis锁:"+lockName+",成功");
        } catch (Exception e) {
            LOGGER.error("释放redis锁失败:"+lockName, e);
        }
        return result;
    }
}
