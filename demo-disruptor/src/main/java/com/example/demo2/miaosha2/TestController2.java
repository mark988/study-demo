package com.example.demo2.miaosha2;

import com.example.demo2.miaosha.*;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author mxg
 */
@Slf4j
@RestController
public class TestController2 {

    @RequestMapping("/c")
    public String test() throws InterruptedException {
        SeckillEventFactory factory = new SeckillEventFactory();
        int ringBufferSize = 1024;
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable);
            }
        };
        //创建disruptor
        Disruptor<SeckillEvent> disruptor = new Disruptor<SeckillEvent>(factory, ringBufferSize, threadFactory);
        //连接消费事件方法
        disruptor.handleEventsWith(new SeckillEventConsumer());
        //启动
        disruptor.start();
        RingBuffer<SeckillEvent> ringBuffer = disruptor.getRingBuffer();
        SeckillEventProducer producer = new SeckillEventProducer(ringBuffer);
        for(long i = 0; i<10; i++){
            producer.seckill(i, i);
        }
        disruptor.shutdown();//关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
        return "success";
    }

}
