package com.example.demo.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/***
 * @author : 马晓光
 * @date   : 2019/7/7
 * @description :
 **/
@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("推送给我的消息:"+msg);
   }
}
