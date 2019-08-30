package com.example.client;

import com.example.client.msg.MessageRequestPacket;
import com.example.client.protocol.PacketCodeC;
import com.example.client.utils.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/***
 * @author : 马晓光
 * @date   : 2019/8/26
 * @description :
 **/
@Slf4j
@Component
public class NettyClient {

    public void start(){
        NioEventLoopGroup workGroup =new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                  .channel(NioSocketChannel.class)
                  .handler(new ChannelInitializer<SocketChannel>() {
                      @Override
                      protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ClientHandler());

                      }
                  });

        bootstrap.connect("localhost",8081).addListener(future -> {
            if(future.isSuccess()){
                log.info("连接服务器成功");
                Channel  channel  = ((ChannelFuture)future).channel();
                startConsoleThread(channel);
            }else{
                log.info("连接服务器失败");
            }
        });
    }
    private   void startConsoleThread(Channel channel) {
        log.info("启动子线程... ");
        new Thread(() -> {
            while (!Thread.interrupted()) {
               // if (LoginUtil.hasLogin(channel)) {
                   //log.info("当前用户已经登录");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();
                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(line);
                    ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channel.alloc(), packet);
                    channel.writeAndFlush(byteBuf);
                //}/*else{
                   // log.info("当前用户没有登录");
                //}*/
            }
        }).start();
    }

}