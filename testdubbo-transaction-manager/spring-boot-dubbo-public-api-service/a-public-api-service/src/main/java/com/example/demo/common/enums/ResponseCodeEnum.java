package com.example.demo.common.enums;

import org.springframework.http.HttpStatus;

/**
 * The enum Rsp status enum.
 *
 * @Author: mxg
 * @Description
 * @Date Created in 2019/1/14 10:18
 */
public enum ResponseCodeEnum {
    /**
     * SUCCESS
     */
    SUCCESS(HttpStatus.OK.value(),"成功"),
    /**
     * Fail rsp status enum.
     */
    FAIL(999,"失败"),
    /**
     * Exception rsp status enum.
     */
    EXCEPTION(500,"系统异常");

    private int code;

    private String message;

    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
