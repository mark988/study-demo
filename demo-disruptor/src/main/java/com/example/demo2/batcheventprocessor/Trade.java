package com.example.demo2.batcheventprocessor;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Trade {
    private Integer id;
    private String  name;
    private Double  price;
    private AtomicInteger count = new AtomicInteger(0);
}
