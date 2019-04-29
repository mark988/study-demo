package com.example.demo;

/**
 * Created by 马晓光 on 2019/4/29.
 */
public class Test {
    public static void main(String[] args) {
        Long sum = 0L;
        long s = System.currentTimeMillis();
        for (long i = 0L; i <=1000000000L ; i++) {
            sum += i;
        }
        long e = System.currentTimeMillis();
        System.out.printf("sum = "+sum +" 消耗时间:"+(e-s));
    }
}
