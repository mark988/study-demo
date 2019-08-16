package com.example.redissubscribe;

import com.example.redissubscribe.component.TopicMessageListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@SpringBootApplication
public class SubscribeApp {

    public static void main(String[] args) {
        SpringApplication.run(SubscribeApp.class, args);
    }

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter( new TopicMessageListener() );
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic( "test" );
    }

    @Bean
    RedisMessageListenerContainer redisContainer(RedisConnectionFactory factory) {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(factory);
        container.addMessageListener(messageListener(),topic());
        return container;
    }
}
