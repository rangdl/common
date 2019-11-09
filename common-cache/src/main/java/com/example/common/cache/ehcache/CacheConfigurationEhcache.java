package com.example.common.cache.ehcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @ClassName CacheConfiguration
 * @Description TODO
 * @Author rdl
 * @Date 2019/11/9 15:06
 * @Version 1.0
 **/
@Configuration
@EnableCaching // 标注启动缓存
@ConditionalOnProperty(name = "spring.cache.synchronize", havingValue = "true")
public class CacheConfigurationEhcache {
    /**
     * Logger for this class
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * ehcache 主要的管理器
     * @param bean
     * @return
     */
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        logger.info("初始化EhCacheCacheManager");
        return new EhCacheCacheManager(bean.getObject());
    }


    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        logger.info("初始化EhCacheManagerFactoryBean");
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();

        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factoryBean.setShared(true);

        return factoryBean;
    }
}
