package com.example.testguavaretrying.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/***
 * @author : 马晓光
 * @date   : 2019/6/1
 * @description :
 **/
@Slf4j
@Service
public class CacheServiceImpl implements CacheService {

    private static final Cache<String, Object> CACHES = CacheBuilder.newBuilder()
            // 最大缓存 100 个
            .maximumSize(10000)
            // 设置缓存过期时间为2小时
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();

    @Override
    public  void  save(String key, Object obj) {
        CACHES.put(key, obj);
    }

    @Override
    public Object get(String key) {
        return CACHES.getIfPresent(key);
    }
}
