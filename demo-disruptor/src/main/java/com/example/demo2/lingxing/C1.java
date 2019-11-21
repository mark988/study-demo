package com.example.demo2.lingxing;

import com.example.demo2.batcheventprocessor.Trade;
import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class C1 implements EventHandler {

    @Override
    public void onEvent(Object event, long sequence, boolean endOfBatch) throws Exception {
        Trade trade = (Trade) event;
        trade.setName("C1");
        log.info("C1  id :{} " , trade.getId());
    }
}
