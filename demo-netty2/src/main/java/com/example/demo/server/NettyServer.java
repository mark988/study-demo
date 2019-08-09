package com.example.demo.server;

import com.sun.corba.se.internal.CosNaming.BootstrapServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/***
 * @author : 马晓光
 * @date   : 2019/7/7
 * @description :
 **/
@Slf4j
@Component
public class NettyServer {

    NioEventLoopGroup boss=new NioEventLoopGroup();
    NioEventLoopGroup work=new NioEventLoopGroup();

    ServerBootstrap serverBootstrap=new ServerBootstrap();
    public void start(){
        serverBootstrap.group(boss,work)
                       .channel(NioServerSocketChannel.class)
                       .childHandler(new ChannelInitializer<NioSocketChannel>() {
                           @Override
                           protected void initChannel(NioSocketChannel ch) throws Exception {
                               ByteBuf byteBuf= Unpooled.copiedBuffer("\r\n".getBytes());
                               ChannelPipeline channelPipeline = ch.pipeline();
                               channelPipeline.addLast(new DelimiterBasedFrameDecoder(1024,byteBuf));
                               channelPipeline.addLast("decoder",new StringDecoder());
                               channelPipeline.addLast("encoder",new StringEncoder());

                               channelPipeline.addLast("handler",new ServerHandler());
                           }
                       });
        try {
            serverBootstrap.bind(8888).sync().addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if(future.isSuccess()){
                        log.info("netty server is starting success.");
                    }else{
                        log.info("netty server is starting failed.");
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
