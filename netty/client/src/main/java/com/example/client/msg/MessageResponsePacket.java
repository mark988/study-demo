package com.example.client.msg;

import com.example.client.protocol.Packet;
import lombok.Data;

import static com.example.client.protocol.command.Command.MESSAGE_RESPONSE;

/***
 * @author : 马晓光
 * @date   : 2019/8/30
 * @description :
 **/
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}