package com.example.deferredresult;

import com.example.deferredresult.entity.User;
import com.example.deferredresult.mapper.UserMapper;
import com.example.deferredresult.v2.AsyncVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
@Component
public class MyTask {

    /**
     * 线程池
     */
    public static ExecutorService FIXED_THREAD_POOL = Executors.newFixedThreadPool(20);

    @Autowired
    private UserMapper userMapper;

    public void run(AsyncVo<Object, Object> vo){
        /**
         * 超时情况
         */
        DeferredResult deferredResult= (DeferredResult)vo.getResult();

        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                log.info("异步线程执行超时");
                deferredResult.setResult("线程执行超时");
            }
        });

        /**
         * 执行完毕情况
         */
        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.info("异步执行完毕");
            }
        });

        /**
         * 通过线程异步执行任务
         */
        FIXED_THREAD_POOL.execute(new Runnable() {
            @Override
            public void run() {
                log.info("异步执行线程:" + Thread.currentThread().getName());
                try {
                    /**
                     * 开始调用后台方法
                     */
                    User u = (User) vo.getParams();
                    int count = userMapper.insert(u);
                    if(count==1){
                        log.info("success");
                        deferredResult.setResult("插入数据成功" );
                    }else{
                        log.info("fail");
                        deferredResult.setResult("--插入数据失败--" );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
