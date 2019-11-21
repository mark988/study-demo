package com.example.demo2.miaosha;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mxg
 */
@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/a")
    public String test() throws InterruptedException {
        DisruptorManager disruptorService=new DisruptorManager();
        disruptorService.init(new Consumer());


        /**
         * 生产者制造数据
         * **/

        CountDownLatch countDownLatch =new CountDownLatch(1);
        UserVO u= new UserVO();
        u.setName("Test");
        u.setUserMapper(userMapper);
        disruptorService.putData(countDownLatch,u);

        //ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

       // executorService.submit(new Provider(disruptor,countDownLatch,userMapper));

        /**
         * 等待生产者完成
         * */

        countDownLatch.await();
        disruptorService.shutdown();



        return "success";
    }


    @RequestMapping("/b")
    public String test2() {
        ExecutorService executorService2 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final CountDownLatch doneSignal = new CountDownLatch(2000);
        for(int i=0;i<1000;i++) {
            int finalI = i;
            executorService2.submit(new Runnable() {
                @Override
                public void run() {
                    User u = new User();
                    u.setName("北国之春");
                    u.setAge(finalI);
                    userMapper.insert(u);
                    //doneSignal.countDown();
                }
            });
        }

        return "ok";
    }
}
