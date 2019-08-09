package com.example.demonetty.client;

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
import java.util.UUID;

/***
 * @author : 马晓光
 * @date   : 2019/7/5
 * @description :
 **/
@Slf4j
public class FirstClientHandler  extends ChannelInboundHandlerAdapter {

   /* @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端准备发送消息 ");
        //创建对象
        LoginRequestPacket requestPacket = new LoginRequestPacket();
        requestPacket.setPwd("123");
        requestPacket.setUserName("admin");
        requestPacket.setUserId(UUID.randomUUID().toString());
        //编码
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(),requestPacket);
        ctx.writeAndFlush(byteBuf);
    }*/

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("flash");
        loginRequestPacket.setPwd("pwd");

        // 编码
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        // 写数据
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if (loginResponsePacket.isSuccess()) {
                System.out.println(new Date() + ": 客户端登录成功");
            } else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        }
    }
    /*@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("【客户端】读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));
    }*/


/*    public ByteBuf getByteBuf(ChannelHandlerContext ctx){
         ByteBuf byteBuf = ctx.alloc().buffer();
         byte[] msg = "Hello netty server ".getBytes(Charset.forName("utf-8"));
         byteBuf.writeBytes(msg);
         return byteBuf;
    }*/
}
