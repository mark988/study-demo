package com.example.testguavaretrying.test;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/***
 * @author : 马晓光
 * @date   : 2019/7/18
 * @description :
 **/
@Slf4j
public class Test {
    static Retryer<Boolean> retryer;

    static {
        // 抛出异常会进行重试
        retryer = RetryerBuilder.<Boolean>newBuilder()
                                .retryIfException()
                                //如果接口返回的结果不符合预期,也需要重试
                                .retryIfResult(Predicates.equalTo(false))
                                //重试策略, 此处设置的是重试间隔时间
                                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                                //重试次数
                                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                                //
                                .build();
    }


    public boolean uploadFile(String fileName) {
        try {
            return retryer.call(new Callable<Boolean>() {
                int count = 0;
                @Override
                public Boolean call() throws Exception {
                    log.info("......执行业务......");
                    //return new PictureService().uploadPicture(fileName, count++);
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void main(String[] args) {
        new Test().uploadFile("testFile");
    }
}
