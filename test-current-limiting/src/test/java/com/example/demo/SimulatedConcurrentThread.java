package com.example.demo;

import java.util.concurrent.CountDownLatch;

/***
 * @author : 马晓光
 * @date   : 2019/5/18
 * @description :
 **/
public class SimulatedConcurrentThread {
    public static CountDownLatch countDownLatch = new CountDownLatch(200);
    public static  Integer THREAD_COUNT = 200;

    public static void main(String[] args){
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(new RequestThread()).start();
            countDownLatch.countDown();
        }
    }
    public static   class  RequestThread implements Runnable{
        @Override
        public void run() {
            //阻塞
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //发送请求
        }
    }
}
