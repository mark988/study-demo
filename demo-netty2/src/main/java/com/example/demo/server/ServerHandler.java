package com.example.demo.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

/***
 * @author : 马晓光
 * @date   : 2019/7/7
 * @description :
 **/
@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        for(Channel ch :channelGroup){
            log.info("服务器接收到"+ctx.channel().remoteAddress()+"端信息:"+msg+" ");
            if(!ch.equals(ctx.channel())){
                ch.writeAndFlush(ctx.channel().remoteAddress()+msg+"\r\n");
            }else{
                ch.writeAndFlush("[我发送的:]"+msg+"\r\n");
            }
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        log.info(ctx.channel().remoteAddress()+"连接了");
        channelGroup.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info(ctx.channel().remoteAddress()+"断开了");
        channelGroup.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("发生异常 , 理由:"+cause.getMessage());
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       // log.info(ctx.channel().remoteAddress()+"上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
       // log.info(ctx.channel().remoteAddress()+"下线了");
    }
}
