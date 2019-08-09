package com.example.demonetty.server;

import com.example.demonetty.protocol.LoginRequestPacket;
import com.example.demonetty.protocol.LoginResponsePacket;
import com.example.demonetty.protocol.Packet;
import com.example.demonetty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.Date;

/***
 * @author : 马晓光
 * @date   : 2019/7/5
 * @description :
 **/
@Slf4j
public class FirstServerHandler  extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(new Date() + ": 客户端开始登录……");
        ByteBuf requestByteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        if (packet instanceof LoginRequestPacket) {
            // 登录流程
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功!");
            } else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }
            // 登录响应
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
   /* @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        LoginResponsePacket responsePacket = new LoginResponsePacket();

       if(packet instanceof LoginRequestPacket){

            LoginRequestPacket requestPacket  =  (LoginRequestPacket)packet;

            if(valid(requestPacket)){
                responsePacket.setSuccess(true);
            }else{
                responsePacket.setSuccess(false);
                responsePacket.setReason("userName is erro or password is erro");
            }
        }

        ctx.writeAndFlush(PacketCodeC.INSTANCE.encode(ctx.alloc(),responsePacket));
    }
*/
    public ByteBuf getByteBuf(ChannelHandlerContext ctx){
        ByteBuf byteBuf = ctx.alloc().buffer();
        byte[] msg = "本周末一起去玩水?".getBytes(Charset.forName("utf-8"));
        byteBuf.writeBytes(msg);
        return byteBuf;
    }
    /**
     * @auth mxg
     * 校验login 是否成功
     *
     * **/
  /*  public boolean valid(LoginRequestPacket byteBuf){
        log.info( "【服务器】"+ byteBuf.getUserName());
        return true;
    }*/
}
