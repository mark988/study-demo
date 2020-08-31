package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
/**
 *
 * @ClassName: WebSocketStompConfig
 * @Description: springboot websocket stomp配置
 * @author cheng
 * @date
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * 注册stomp的端点
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 允许使用socketJs方式访问，访问点为webSocketServer，允许跨域
        // 在网页上我们就可以通过这个链接
        // http://localhost:8080/webSocketServer
        // 来和服务器的WebSocket连接
        registry.addEndpoint("/webSocketServer").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 配置信息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /**
         * 订阅Broker名称
         * 用户订阅主题的前缀 第一个对个人 ，第二个全部发送
         */

        registry.enableSimpleBroker("/queue", "/topic");
        /**
         * 全局使用的消息前缀（客户端订阅路径上会体现出来）
         * 发往应用程序的消息将会带有“/app”前缀
         * **/
        registry.setApplicationDestinationPrefixes("/app");
        /**
         *  点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
         *  registry.setUserDestinationPrefix("/user/");
         */
    }

    /**
     * 配置客户端入站通道拦截器
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(createUserInterceptor());
    }

    /**
     *
     * @Title: createUserInterceptor
     * @Description: 将客户端渠道拦截器加入spring ioc容器
     * @return
     */
    @Bean
    public CustomerUserInterceptor createUserInterceptor() {
        return new CustomerUserInterceptor();
    }

}