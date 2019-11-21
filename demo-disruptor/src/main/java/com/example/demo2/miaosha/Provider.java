package com.example.demo2.miaosha;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class Provider implements Runnable {

    private Disruptor<UserVO> disruptor;
    private CountDownLatch countDownLatch;
    private UserMapper userMapper;

    private final  Integer LOOP = 1000;

    public Provider(Disruptor disruptor,CountDownLatch countDownLatch,UserMapper userMapper){
        this.disruptor = disruptor;
        this.countDownLatch = countDownLatch;
        this.userMapper = userMapper;
    }

    @Override
    public void run() {
        for (int i=0;i<LOOP;i++) {
            int finalI = i;
            disruptor.publishEvent(new EventTranslator<UserVO>() {
                @Override
                public void translateTo(UserVO event, long sequence) {
                    event.setName("polo_"+ finalI);
                    event.setAge(finalI);
                    event.setEmail("9090@qq.com");
                    event.setUserMapper(userMapper);
                }
            });
            log.info("date:{}",new Date());
        }
        //释放锁
        countDownLatch.countDown();
    }
}
