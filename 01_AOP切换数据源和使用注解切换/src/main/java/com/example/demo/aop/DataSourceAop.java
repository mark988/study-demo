package com.example.demo.aop;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.extern.java.Log;

/**
 * Copyright: Copyright (c) 2019
 * <p> 说明：动态数据源配置 </P>
 *
 * @version: V1.0
 * @author: BianPeng
 *
 */
@Aspect
@Component
@Order(0)
@Lazy(false)
@Slf4j
public class DataSourceAop{

    private static final String MASTER = "master";

    private static final String SLAVE = "slave";
    @Pointcut("execution(* com.example.demo.service..*.*(..)) || execution(* com.baomidou.mybatisplus.extension.service..*.*(..))")
    public void checkArgs() {
    }


    @Before("checkArgs()")
    public void process(JoinPoint joinPoint) throws NoSuchMethodException, SecurityException {
        String methodName = joinPoint.getSignature().getName();
        Class clazz = joinPoint.getTarget().getClass();
        if(clazz.isAnnotationPresent(DS.class)){
            //获取类上注解
            return;
        }

        String targetName = clazz.getSimpleName();
        Class[] parameterTypes =
                ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
        Method methdo = clazz.getMethod(methodName,parameterTypes);
        if (methdo.isAnnotationPresent(DS.class)) {
            return;
        }
        log.info(" 方法名称：{}",methodName);
        if (methodName.startsWith("get")
                || methodName.startsWith("count")
                || methodName.startsWith("find")
                || methodName.startsWith("list")
                || methodName.startsWith("select")
                || methodName.startsWith("check")
                || methodName.startsWith("page")) {

            log.info("当前执行的库："+SLAVE);
            DynamicDataSourceContextHolder.push(SLAVE);
        } else {
            log.info("当前执行的库："+MASTER);
            DynamicDataSourceContextHolder.push(MASTER);
        }
    }
    @After("checkArgs()")
    public void afterAdvice(){
        DynamicDataSourceContextHolder.clear();
    }
}
