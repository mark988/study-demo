package com.example.demo2;

import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderHandler implements EventHandler {

    @Override
    public void onEvent(Object event, long sequence, boolean endOfBatch) throws Exception {
        Order order = (Order)  event;
        System.out.println("name:"+order.getName()+" id:"+order.getId()+ " sequence:"+sequence );
    }
}
