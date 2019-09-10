package com.example.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/***
 * @author : Administrator
 * @date   : 2019/9/2 0002
 * @description :
 **/
@Slf4j
public class InBoundHandlerA extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("InBoundHandlerA: " + "google.com");
        super.channelRead(ctx, msg);
    }
}
