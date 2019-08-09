package com.example.testguavaretrying.component;

import com.example.testguavaretrying.listener.MyRetryerListener;
import com.github.rholder.retry.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/***
 * @author : 马晓光
 * @date   : 2019/7/18
 * @description :
 **/
@Component
public class ProductRetryerBuilder {
    public Retryer build() {
        //定义重试机制
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfException()
                //等待策略：每次请求间隔1s
                .withWaitStrategy(WaitStrategies.fixedWait(10, TimeUnit.SECONDS))
                //停止策略 : 尝试请求3次
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                //时间限制 : 某次请求不得超过2s , 类似: TimeLimiter timeLimiter = new SimpleTimeLimiter();
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))
                .build();
                //默认的阻塞策略：线程睡眠
                //.withBlockStrategy(BlockStrategies.threadSleepStrategy())
                //自定义阻塞策略：自旋锁
                //.withBlockStrategy(new SpinBlockStrategy())
                //自定义重试监听器
                //.withRetryListener(new MyRetryerListener()).build();
        return retryer;
    }
}
