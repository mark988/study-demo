package com.example.testguavaretrying.service;

import com.example.testguavaretrying.component.ProductRetryerBuilder;
import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/***
 * @author : 马晓光
 * @date   : 2019/7/18
 * @description :
 **/
@Slf4j
@Service
public class TestServiceImpl implements TestService{

    static Retryer<Object> retryer;

    static {
        retryer = RetryerBuilder.<Object>newBuilder()
                .retryIfException() // 抛出异常会进行重试
                .retryIfResult(Predicates.equalTo(null))
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(3, TimeUnit.SECONDS))
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS)) // 重试策略, 此处设置的是重试间隔时间
                .withStopStrategy(StopStrategies.stopAfterAttempt(5)) // 重试次数
                .build();
    }


    @Override
    public List<String> query() throws InterruptedException {
        log.info("begin query ....");

        List<String> res = new ArrayList<>();
        try {

            retryer.call(new Callable() {
                @Override
                public Object call() throws Exception {
                    long start=System.currentTimeMillis();
                    long end = 0L ;
                    log.info("数据抓取有点慢,查询持续数据中...");
                    Thread.sleep(10000);

                    res.add("Mark");
                    res.add("Jack");
                    res.add("Tom");
                    res.add("Merry");
                    log.info("返回结果");
                   // double x = 1/0;
                  //  cacheService.save("list",res);
                    end = System.currentTimeMillis();
                    log.info("操作时间消耗:"+(end-start)/1000 +"秒");
                    return res;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (RetryException e) {
            log.info("send to email...");
            e.printStackTrace();
            return null;
        }

        return res;
    }
}
