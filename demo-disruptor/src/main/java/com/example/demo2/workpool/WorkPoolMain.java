package com.example.demo2.workpool;

import com.example.demo2.batcheventprocessor.Trade;
import com.lmax.disruptor.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class WorkPoolMain {

        public static void main(String[] args) throws InterruptedException {

            RingBuffer<Trade> ringBuffer =  RingBuffer.createSingleProducer(new EventFactory<Trade>() {
                @Override
                public Trade newInstance() {
                    return new Trade();
                }
            },1024,new BusySpinWaitStrategy());

            SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
            WorkerPool<Trade> workerPool=new WorkerPool<Trade>(ringBuffer,sequenceBarrier,
                                                              new IgnoreExceptionHandler(),new MyWorkHandler());

            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            workerPool.start(executorService);

            /**
             * 生产者
             * */
            for(int i =0 ;i <10 ; i++ ){
                long seq =  ringBuffer.next();
                ringBuffer.get(seq).setName(" watch-"+i);
                ringBuffer.get(seq).setPrice(Math.random()* 999);
                ringBuffer.publish(seq);
            }

            Thread.sleep(2000);
            /**
             *
             * **/
            workerPool.halt();

            executorService.shutdown();

        }
}
