package com.example.demo.aop2;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
@Aspect
@Component
public class AuthorizationAspect {
    @Pointcut("@annotation(com.example.demo.aop2.EagleEye)")
    public void eagleEye() {

    }

    @Around("eagleEye()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long begin =System.currentTimeMillis();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =attributes.getRequest();
        // 获取请求头
        Enumeration<String> enumeration = request.getHeaderNames();
        StringBuffer headers = new StringBuffer();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            headers.append(name + ":" + value).append(",");
        }
        log.info(" 头信息:{}",headers.toString());
      /*  Enumeration<String> parameterNames = request.getParameterNames();
        StringBuffer parameterNamesStr = new StringBuffer();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String value = request.getParameterValues(name);
            parameterNamesStr.append(name + ":" + value).append(",");
        }*/


        Signature signature = pjp.getSignature();;
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method =  methodSignature.getMethod();
        EagleEye eagleEye  = method.getAnnotation(EagleEye.class);
        //接口描述信息
        String desc =  eagleEye.desc();
        log.info("================请求开始==================");
        log.info("请求链接：{}",request.getRequestURL().toString());
        log.info("请求描述：{}",desc);
        log.info("请求类型: {}",request.getMethod());
        log.info("请求方法：{},{}",signature.getDeclaringTypeName(),signature.getName());
        log.info("请求入参：{}", JSON.toJSONString(pjp.getArgs()));
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info("请求耗时:{} 毫秒",end-begin);
        log.info("返回结果:{}",JSON.toJSONString(result));
        log.info("================请求结束==================");
        return result;
    }
}
