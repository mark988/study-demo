package com.example.demo2;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mark
 */
public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        int ringBufferSize = 4;

        /**
         *  定义disruptor
         */
        Disruptor<Order> disruptor = new Disruptor<Order>(new EventFactory<Order>() {
            @Override
            public Order newInstance() {
                return new Order();
            }
        }, ringBufferSize, executorService, ProducerType.SINGLE, new YieldingWaitStrategy());

        /**
         * 指定消费者
         */
        disruptor.handleEventsWith(new OrderHandler());

        /**
         * start()
         */
        disruptor.start();

        /**
         * 生产者生产数据
         *
         */
        RingBuffer<Order>  orderRingBuffer= disruptor.getRingBuffer();
        for(int i =0;i<10;i++){
            long sequence =  orderRingBuffer.next();
            Order order = orderRingBuffer.get(sequence);
            order.setId(i);
            order.setName("black Jack-"+i);
            orderRingBuffer.publish(sequence);
        }

        /**
         * shutdown
         */
        disruptor.shutdown();
        executorService.shutdown();
    }

}
