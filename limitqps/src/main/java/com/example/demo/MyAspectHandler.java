package com.example.demo;

import cn.yueshutong.springbootstartercurrentlimiting.annotation.CurrentLimiter;
import cn.yueshutong.springbootstartercurrentlimiting.handler.CurrentAspectHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * Created by 马晓光 on 2019/5/2.
 * 针对被注解的方法进行自定义拒绝策略
 * */
@Component
public class MyAspectHandler implements CurrentAspectHandler {

    @Override
    public Object around(ProceedingJoinPoint pjp, CurrentLimiter rateLimiter) throws Throwable {

        return "用户已经满了";
    }
}
