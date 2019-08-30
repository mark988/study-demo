package com.example.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/***
 * @author : 马晓光
 * @date   : 2019/8/26
 * @description :
 **/
@Slf4j
@Component
public class NettyServer {

    public void start(){
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss,work)
                        //指定IO模型
                       .channel(NioServerSocketChannel.class)
                       //定义后续每条连接的数据读写，业务处理逻辑
                       .childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ServerHandler());
            }
        });
        serverBootstrap.bind(8081);
    }
}
