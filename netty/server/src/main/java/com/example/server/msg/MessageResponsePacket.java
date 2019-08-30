package com.example.server.msg;

import com.example.server.protocol.Packet;
import static com.example.server.protocol.command.Command.MESSAGE_RESPONSE;
import lombok.Data;
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