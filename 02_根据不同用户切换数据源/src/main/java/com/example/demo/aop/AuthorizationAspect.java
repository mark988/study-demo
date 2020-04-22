package com.example.demo.aop;

import com.alibaba.fastjson.JSON;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
@Aspect
@Component
public class AuthorizationAspect {

    private static final String MASTER = "master";
    private static final String SLAVE = "slave";

    @Pointcut("@annotation(com.example.demo.aop.EagleEye)")
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

        String tokenValue = request.getHeader("token");

        if(StringUtils.isEmpty(tokenValue)){
            return null;
        }
        /**
         * 根据前端传递过来的token判断选中使用那个数据库
         */
        if(tokenValue.equals("1000")){
            DynamicDataSourceContextHolder.push(SLAVE);
        }else{
            DynamicDataSourceContextHolder.push(MASTER);
        }

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

    @After("eagleEye()")
    public void afterAdvice(){
        DynamicDataSourceContextHolder.clear();
    }
}
