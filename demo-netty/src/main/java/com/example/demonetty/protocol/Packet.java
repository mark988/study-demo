package com.example.demonetty.protocol;

/***
 * @author : 马晓光
 * @date   : 2019/7/5
 * @description :
 **/
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;


    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
