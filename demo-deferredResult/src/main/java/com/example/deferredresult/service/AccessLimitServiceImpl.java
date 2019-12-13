package com.example.deferredresult.service;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

/**
 * @author maxiaoguang
 */
@Service
public class AccessLimitServiceImpl {

    /**
     * 每秒只发出5个令牌
     */
    RateLimiter rateLimiter = RateLimiter.create(0.5);

    /**
     * 尝试获取令牌
     * @return
     */
    public boolean tryAcquire(){
        return rateLimiter.tryAcquire();
    }
}
