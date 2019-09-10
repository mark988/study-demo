package com.example.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/***
 * @author : Administrator
 * @date   : 2019/9/2 0002
 * @description :
 **/
@Slf4j
public class OutBoundHandlerC extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("OutBoundHandlerC: " + msg);
        super.write(ctx, msg, promise);
    }
    
}
