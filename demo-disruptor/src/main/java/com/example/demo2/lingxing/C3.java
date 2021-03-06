package com.example.demo2.lingxing;

import com.example.demo2.batcheventprocessor.Trade;
import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class C3 implements EventHandler {

    @Override
    public void onEvent(Object event, long sequence, boolean endOfBatch) throws Exception {

        Trade trade = (Trade) event;

        log.info("C3 id:{} , name:{} ,  price :{} ", trade.getId(),trade.getName(),trade.getPrice());
    }
}
