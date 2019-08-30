package com.example.server;

import com.example.server.msg.MessageRequestPacket;
import com.example.server.msg.MessageResponsePacket;
import com.example.server.protocol.Packet;
import com.example.server.protocol.PacketCodeC;
import com.example.server.protocol.request.LoginRequestPacket;
import com.example.server.protocol.response.LoginResponsePacket;
import com.example.server.utils.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import java.nio.charset.Charset;
import java.util.Date;

/***
 * @author :
 * @date   : 2019/8/26
 * @description :
 **/
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        ByteBuf requestByteBuf = (ByteBuf) msg;
        // 解码
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);
        // 判断是否是登录请求数据包
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            // 登录校验
            if (valid(loginRequestPacket)) {
                LoginUtil.markAsLogin(ctx.channel());
                loginResponsePacket.setSuccess(true);
                // 校验成功
                log.info("用户名和密码校验成功");
            } else {
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("user name is erro or passwd erro");
                // 校验失败
                log.info("用户名和密码校验失败");
            }
            // 登录响应
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        } else if (packet instanceof MessageRequestPacket) {
            // 处理消息
            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }



    private ByteBuf getByteBuf(ChannelHandlerContext ctx,String msg) {
        // 1. 获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();
        // 2. 准备数据，指定字符串的字符集为 utf-8
        byte[] bytes = msg.getBytes(Charset.forName("utf-8"));
        // 3. 填充数据到 ByteBuf
        buffer.writeBytes(bytes);
        return buffer;
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        if(loginRequestPacket==null){
            return false;
        }
        if(loginRequestPacket.getUsername().equals("mark")){
            return true;
        }
        return false;
    }
}
