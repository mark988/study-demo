package com.example.demo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/***
 * @author : 马晓光
 * @date   : 2019/7/7
 * @description :
 **/
@Slf4j
public class NettyClient2 {

    public static void main(String[] args) {


        NioEventLoopGroup work = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        //public void start () {
            bootstrap.group(work)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ByteBuf byteBuf= Unpooled.copiedBuffer("\r\n".getBytes());
                            ChannelPipeline channelPipeline = ch.pipeline();
                            channelPipeline.addLast(new DelimiterBasedFrameDecoder(1024,byteBuf));

                            channelPipeline.addLast("encoder", new StringEncoder());
                            channelPipeline.addLast("decoder", new StringDecoder());
                            channelPipeline.addLast("handler", new ClientHandler());
                        }
                    });


            try {
                Channel ch = bootstrap.connect("127.0.0.1", 8888).channel();
                while (true) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    ch.writeAndFlush(bufferedReader.readLine() + "\r\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        //}
    }
}
