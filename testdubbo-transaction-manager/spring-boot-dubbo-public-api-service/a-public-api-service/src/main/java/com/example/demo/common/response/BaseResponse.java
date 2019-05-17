package com.example.demo.common.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 基本返回
 *
 * @author: mxg
 * @date: 2018-07-03 16:46
 */
@Data
public class BaseResponse implements Serializable {

    private int status = HttpStatus.OK.value();

    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
