package com.example.client.protocol.request;

import com.example.client.protocol.Packet;
import lombok.Data;

import static com.example.client.protocol.command.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
