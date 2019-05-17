package com.example.demo.common.aop;
import com.example.demo.common.enums.ResponseCodeEnum;
import com.example.demo.common.exception.DefaultException;
import com.example.demo.common.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: mxg
 * @Description  全局异常处理
 * @Date Created in 2019/1/9 14:12
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ObjectResponse exceptionHandler(Exception e){
        log.error("【系统抛出Exception异常】 —— 异常内容如下：{}" , e);
        ObjectResponse objectResponse = new ObjectResponse<>();
        objectResponse.setStatus(ResponseCodeEnum.FAIL.getCode());
        objectResponse.setMessage(ResponseCodeEnum.FAIL.getMessage());
        return objectResponse;
    }

    @ExceptionHandler(DefaultException.class)
    @ResponseBody
    public ObjectResponse defaultException(DefaultException e){
        log.error("【系统抛出SinochemException异常】 —— 异常内容如下：{}" , e);
        ObjectResponse objectResponse = new ObjectResponse<>();
        objectResponse.setStatus(ResponseCodeEnum.FAIL.getCode());
        objectResponse.setMessage(ResponseCodeEnum.FAIL.getMessage());
        return objectResponse;
    }
}
