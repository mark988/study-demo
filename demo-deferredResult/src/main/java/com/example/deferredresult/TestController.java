package com.example.deferredresult;

import com.example.deferredresult.entity.User;
import com.example.deferredresult.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RestController
public class TestController {

    /**
     * 线程池
     */
    public static ExecutorService FIXED_THREAD_POOL = Executors.newFixedThreadPool(20);

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/test0",method = RequestMethod.GET)
    public String test0(@RequestParam String name, @RequestParam Integer age){
        log.info(" ----------test0----------------");
        User u = new User();
        u.setAge(age);
        u.setName(name);
        int count = userMapper.insert(u);
        if(count!=1){
            return "erro";
        }
        return "success";
    }

    /**
     * 例子1
     * @return
     */
    @GetMapping("/test")
    public DeferredResult<String> test(){
        //设置10秒超时
        DeferredResult deferredResult = new DeferredResult(10000L);
        //模拟耗时操作
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        deferredResult.setResult("OK");
        return deferredResult;
    }

    /**
     * 例子2
     * @return
     * @throws Exception
     */
    @RequestMapping("/test2")
    public DeferredResult<String> deferredResult(@RequestParam String name, @RequestParam Integer age) throws Exception {
        log.info("控制层执行线程:" + Thread.currentThread().getName());
        /**
         * 设置超时
         */
        DeferredResult<String> deferredResult = new DeferredResult<String>(10*1000L);
        /**
         * 超时情况
         */
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
                    User u = new User();
                    u.setAge(age);
                    u.setName(name);
                    int count = userMapper.insert(u);
                    if(count==1){
                        log.info("success");
                        deferredResult.setResult("插入数据成功" );
                    }else{
                        log.info("fail");
                        deferredResult.setResult("--插入数据失败--" );
                    }

                    //deferredResult.setResult("这是【异步】的请求返回: 查询完毕" );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        /**
         * 返回结果
         */
        return deferredResult;
    }



}
