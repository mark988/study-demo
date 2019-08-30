package com.example.client.msg;

import com.example.client.protocol.Packet;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import static com.example.client.protocol.command.Command.MESSAGE_REQUEST;
import lombok.Data;
/***
 * @author : 马晓光
 * @date   : 2019/8/30
 * @description :
 **/
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}