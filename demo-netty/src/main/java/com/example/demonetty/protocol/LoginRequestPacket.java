package com.example.demonetty.protocol;

import static com.example.demonetty.protocol.Command.LOGIN_REQUEST;
import lombok.Data;
/***
 * @author : 马晓光
 * @date   : 2019/7/5
 * @description :
 **/
@Data
public class LoginRequestPacket extends Packet {

    private String userId ;
    private String userName;
    private String pwd;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
