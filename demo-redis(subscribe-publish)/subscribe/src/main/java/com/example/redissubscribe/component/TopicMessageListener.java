package com.example.redissubscribe.component;

import com.example.redissubscribe.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/***
 * @author : 马晓光
 * @date   : 2019/8/13
 * @description :
 **/
@Slf4j
public class TopicMessageListener implements MessageListener {

    @Autowired
    private TestService testService;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body   = message.getBody();
        byte[] channel = message.getChannel();

        log.info("通道:{} , value:{}",new String(channel),new String( body));
        testService.saveObject();
    }
}
