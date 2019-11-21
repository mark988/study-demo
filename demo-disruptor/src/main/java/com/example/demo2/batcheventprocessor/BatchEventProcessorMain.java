package com.example.demo2.batcheventprocessor;

import com.lmax.disruptor.*;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 使用消息处理器
 */
public class BatchEventProcessorMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        /**
         * 定义ringbuffer
         * */
        final RingBuffer<Trade> ringBuffer= RingBuffer.createSingleProducer(new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                return new Trade();
            }
        },1024,new BusySpinWaitStrategy()) ;
        /**
         *
         * */
        SequenceBarrier  sequenceBarrier = ringBuffer.newBarrier();
        BatchEventProcessor<Trade> tradeBatchEventProcessor=new BatchEventProcessor<>(ringBuffer,sequenceBarrier,new TradeEventHandler());

        executorService.submit(tradeBatchEventProcessor);
        //多个消费者时候需要设置屏障
        ringBuffer.addGatingSequences(tradeBatchEventProcessor.getSequence());
        /**
         * 生产者
         * */
      /*  Future<?> future=executorService.submit(
            new Callable<Void>(){
                @Override
                public Void call() throws Exception {
                    for(int i =0;i<10;i ++) {
                        long seq = ringBuffer.next();
                        ringBuffer.get(seq).setPrice(Math.random() * 999);
                        ringBuffer.get(seq).setName(" apple_"+i);
                        ringBuffer.publish(seq);
                    }
                    return null;
                }
        });*/

        for(int i =0;i<10;i ++) {
            long seq = ringBuffer.next();
            ringBuffer.get(seq).setPrice(Math.random() * 999);
            ringBuffer.get(seq).setName(" apple_"+i);
            ringBuffer.publish(seq);
        }


        /**
         * 等待生产者结束
         */
        //future.get();
        /**
         * 等消费者结束
         * */
        Thread.sleep(2000);
        /**
         * 通知事件处理器可以结束了
         * */
        tradeBatchEventProcessor.halt();

        executorService.shutdown();
    }
}
