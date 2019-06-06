package com.example.demo.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/***
 * @author : 马晓光
 * @date   : 2019/6/2
 * @description :
 **/

@Slf4j
@Aspect
@Configuration
public class PreventDuplicateSubmitAspect {
    private static final Cache<String, Object> CACHES = CacheBuilder.newBuilder()
            // 最大缓存 100 个
            .maximumSize(100)
            // 设置缓存过期时间为5S
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();

    @Around("execution(public * *(..)) && @annotation(com.example.demo.utils.PreventDuplicateSubmit)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        PreventDuplicateSubmit preventDuplicateSubmit = method.getAnnotation(PreventDuplicateSubmit.class);
        String key = getCacheKey(preventDuplicateSubmit, pjp.getArgs());
        if (!StringUtils.isEmpty(key)) {
            if (CACHES.getIfPresent(key) != null) {
                log.info("重复请求异常");
               // throw new RuntimeException("请勿重复请求");
              return  ResponseEntity.status(HttpStatus.OK).body("请勿重复请求");
            }
            // 如果是第一次请求,就将key存入缓存中
            CACHES.put(key, key);
        }
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("服务器异常");
        } finally {
            //如果演示的话需要注释该代码,手动将缓存清除，实际应该放开
            //CACHES.invalidate(key);
        }
    }

    /**
     * Cache key生成策略，这里可以自定义实现，比如再Submit注解中添加需要从request中获取字段名称，在此方法中通过反射获取，拼接为最终的Cache key
     * 本方法Cache key使用最简单的策略：prefix + request参数的toString，这里只做展示使用，一般不会使用这种策略，一是会导致cache key过长，浪费存储空间，
     * 二是，如果请求参数没有实现toString方法，对于相同的请求参数，依然会被认为是两个不同的请求
     */
    private String getCacheKey(PreventDuplicateSubmit preventDuplicateSubmit, Object[] args) {
        String prefix = preventDuplicateSubmit.prefix();
        return prefix + args[0];
    }
}
