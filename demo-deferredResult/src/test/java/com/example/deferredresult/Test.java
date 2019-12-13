package com.example.deferredresult;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Test {
    /**
     * 模拟10个线程同时访问接口
     * @param args
     */
    public static void main(String[] args){
        Integer THREAD_NUMBER= 10;
        ExecutorService executorService= Executors.newFixedThreadPool(THREAD_NUMBER);
        RestTemplate restTemplate = new RestTemplate();

        for(int i=0;i<THREAD_NUMBER;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                     String result =  restTemplate.getForObject("http://localhost:8080/test5",String.class);
                     log.info("result:{}",result);
                }
            });
        }
    }
}
