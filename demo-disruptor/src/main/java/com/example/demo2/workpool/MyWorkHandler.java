package com.example.demo2.workpool;

import com.example.demo2.batcheventprocessor.Trade;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyWorkHandler implements WorkHandler {

    @Override
    public void onEvent(Object event) throws Exception {
        Trade trade = (Trade) event;
        log.info("name :{} ,price :{}",trade.getName(),trade.getPrice());
    }
}
