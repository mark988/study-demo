package com.example.demo2.lingxing;

import com.example.demo2.batcheventprocessor.Trade;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class P implements Runnable {

    private CountDownLatch countDownLatch;
    private Disruptor<Trade> disruptor;
    private final Integer LOOP = 10;

    public P(Disruptor disruptor,CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
        this.disruptor = disruptor;
    }

    @Override
    public void run() {
        for (int i = 0; i < LOOP; i++){
            int finalI = i;
            disruptor.publishEvent(new EventTranslator<Trade>() {
                @Override
                public void translateTo(Trade event, long sequence) {
                    event.setId(finalI);
                }
            });

        }
        countDownLatch.countDown();
    }
}
