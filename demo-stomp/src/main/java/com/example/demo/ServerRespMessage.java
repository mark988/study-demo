package com.example.demo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 服务器相应消息
 * @author maxiaoguang
 */
@Builder
@Data
public class ServerRespMessage implements Serializable {
    private String responseMessage;
}
