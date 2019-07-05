package com.example.demonetty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/***
 * @author : 马晓光
 * @date   : 2019/7/5
 * @description :
 **/
@Slf4j
public class FirstServerHandler  extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ctx.fireChannelRead(msg);
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("【服务器】我接收到的消息:"+byteBuf.toString(Charset.forName("utf-8")));
        //服务器向客户端发送数据
        ctx.writeAndFlush(getByteBuf(ctx));
    }

    public ByteBuf getByteBuf(ChannelHandlerContext ctx){
        ByteBuf byteBuf = ctx.alloc().buffer();
        byte[] msg = "本周末一起去玩水?".getBytes(Charset.forName("utf-8"));
        byteBuf.writeBytes(msg);
        return byteBuf;
    }
}
