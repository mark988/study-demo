package com.example.demo2.batcheventprocessor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mark
 */
@Slf4j
public class TradeEventHandler implements EventHandler<Trade>{ //, WorkHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {

        log.info("name:{} price:{}",event.getName(),event.getPrice());
    }

  /*  @Override
    public void onEvent(Trade event) throws Exception {
        log.info("name:{} price:{}",event.getName(),event.getPrice());
    }*/
}
