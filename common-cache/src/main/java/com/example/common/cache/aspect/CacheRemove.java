package com.example.common.cache.aspect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName CacheRemove
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/18 14:12
 * @Version 1.0
 **/
@Target({ java.lang.annotation.ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheRemove {
    //    设置default 值,则属性为非必填项
    String value();//cacheName
    String key() default "";//key
    String[] keys() default {};//key数组
    String keyRegex() default "";//key 模糊匹配
    boolean allEntries() default false;//是否清除此cache下所有缓存
}
