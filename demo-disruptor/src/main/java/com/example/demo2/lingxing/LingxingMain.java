package com.example.demo2.lingxing;

import com.example.demo2.batcheventprocessor.Trade;
import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LingxingMain {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Disruptor<Trade> disruptor = new Disruptor<Trade>(new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                return new Trade();
            }
        }, 1024, executorService, ProducerType.SINGLE,new BusySpinWaitStrategy());

        EventHandlerGroup<Trade>  eventHandlerGroup= disruptor.handleEventsWith(new C1(),new C2());
        eventHandlerGroup.then(new C3());

        disruptor.start();
        /**
         *
         * 生产者制造数据
         * **/

       CountDownLatch countDownLatch =new CountDownLatch(1);
       executorService.submit(new P(disruptor,countDownLatch));

       /**
        * 等待生产者完成
        * */
       countDownLatch.await();

        disruptor.shutdown();
        disruptor.halt();

        executorService.shutdown();
    }
}
