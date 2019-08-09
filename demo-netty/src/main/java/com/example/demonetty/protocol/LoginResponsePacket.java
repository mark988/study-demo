package com.example.demonetty.protocol;

import lombok.Data;
import static com.example.demonetty.protocol.Command.LOGIN_RESPONSE;

/***
 * @author : 马晓光
 * @date   : 2019/7/5
 * @description :
 **/
@Data
public class LoginResponsePacket extends Packet {

    private boolean  success ;
    private String   reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
