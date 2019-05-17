package com.example.demo.common.exception;

import com.example.demo.common.enums.ResponseCodeEnum;

/**
 * @Author: mxg
 * @Description  自定义异常
 * @Date Created in 2019/1/15 9:44
 */
public class DefaultException extends RuntimeException{

    private ResponseCodeEnum rspStatusEnum;

    public DefaultException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultException(ResponseCodeEnum rspStatusEnum) {
        super(rspStatusEnum.getMessage());
        this.rspStatusEnum = rspStatusEnum;
    }

    public DefaultException(ResponseCodeEnum rspStatusEnum, Throwable cause) {
        super(rspStatusEnum.getMessage(), cause);
        this.rspStatusEnum = rspStatusEnum;
    }

    public ResponseCodeEnum getRspStatusEnum() {
        return rspStatusEnum;
    }

    public void setRspStatusEnum(ResponseCodeEnum rspStatusEnum) {
        this.rspStatusEnum = rspStatusEnum;
    }
}
