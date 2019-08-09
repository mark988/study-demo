package com.example.demonetty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Component;

/***
 * @author : 马晓光
 * @date   : 2019/7/5
 * @description :
 **/

@Component
public class NettyServer {

    public  void start(){

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workGroup)
                       .channel(NioServerSocketChannel.class)
                       .option(ChannelOption.SO_BACKLOG, 1024)
                       .childOption(ChannelOption.SO_KEEPALIVE, true)
                       .childOption(ChannelOption.TCP_NODELAY, true)
                       .childHandler(new ChannelInitializer<NioSocketChannel>() {

                           @Override
                           protected void initChannel(NioSocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new FirstServerHandler());
                           }
                       });
        serverBootstrap.bind(8888);
    }

}
