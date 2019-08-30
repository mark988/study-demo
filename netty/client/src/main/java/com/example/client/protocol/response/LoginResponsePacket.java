package com.example.client.protocol.response;

import com.example.client.protocol.Packet;
import lombok.Data;

import static com.example.client.protocol.command.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
