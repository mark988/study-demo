package com.example.demonetty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.Charset;

/***
 * @author : 马晓光
 * @date   : 2019/7/5
 * @description :
 **/
public class FirstClientHandler  extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(getByteBuf(ctx));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("【客户端】读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));
    }

    public ByteBuf getByteBuf(ChannelHandlerContext ctx){
         ByteBuf byteBuf = ctx.alloc().buffer();
         byte[] msg = "Hello netty server ".getBytes(Charset.forName("utf-8"));
         byteBuf.writeBytes(msg);
         return byteBuf;
    }
}
