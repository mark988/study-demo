package com.example.demolettuceredis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoLettuceRedisApplicationTests {

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Test
    public void contextLoads() {
        log.info("----------------------------");
        User u=new User();
        u.setUsername("mark");
        u.setPassword("123456");
        redisCacheTemplate.opsForValue().set("testuser", u);
        log.info("{}",redisCacheTemplate.opsForValue().get("testuser"));

        redisCacheTemplate.opsForValue().set("testuser","hello",5, TimeUnit.SECONDS);

        log.info("["+redisCacheTemplate.opsForValue().get("testuser1")+"]");
    }

}
