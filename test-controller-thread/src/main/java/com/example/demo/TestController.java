package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 * @author : 马晓光
 * @date   : 2019/6/1
 * @description :
 **/
@Slf4j
@RestController
public class TestController {
    private int x = 0;
    //注意这个地方
    /**
     *
     * **/
    Lock lock=new ReentrantLock();
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(HttpServletRequest request, HttpServletResponse response){
         try {
             lock.lock();
             x++;
             log.info("{}",x);
         }catch (Exception e){

         }finally {
             lock.unlock();
         }

        return "hello world "+request.getHeader("Thread");
    }


    private AtomicInteger atomicInteger=new AtomicInteger(1);

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public String test2(HttpServletRequest request, HttpServletResponse response){
       Integer x = atomicInteger.incrementAndGet();
       log.info("{}",x);

        return "hello world x="+x ;
    }

    /**
     * 测试map结构安全性
     *
     * **/
    @Autowired
    private IDataSaveService iDataSaveService;
    @RequestMapping(value = "/test3",method = RequestMethod.GET)
    public String test3(){
        for(int i=0;i<1000;i++){
            iDataSaveService.saveToMem(i+"",i);
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            iDataSaveService.saveToMem2(i+"",i);
        }
        log.info(" current map size: {}",iDataSaveService.sizeConcurrentMap());
        log.info("  map size: {}",iDataSaveService.sizeHashMap());
        return "hello world" ;
    }
}
