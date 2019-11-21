package com.example.demo2.miaosha;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class DisruptorManager  {

    private Disruptor<UserVO> disruptor = null;

    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    public  void  init(Consumer consumer){

        disruptor = new Disruptor<UserVO>(new EventFactory<UserVO>() {
           @Override
           public UserVO newInstance() {
               return new UserVO();
           }
       },1024*1024, executorService, ProducerType.SINGLE,new BusySpinWaitStrategy());

        disruptor.handleEventsWith(consumer);
        disruptor.start();
    }

    public void putData(CountDownLatch countDownLatch, UserVO userVO){
        disruptor.publishEvent(new EventTranslator<UserVO>() {
            @Override
            public void translateTo(UserVO event, long sequence) {
                log.info(" userVO:{} ",userVO.toString());
                 BeanUtils.copyProperties(userVO,event);
            }
        });
        countDownLatch.countDown();

    }

    public void shutdown(){
        disruptor.halt();
        disruptor.shutdown();

    }
}
