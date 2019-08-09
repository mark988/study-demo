package com.example.testguavaretrying.listener;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import lombok.extern.slf4j.Slf4j;

/***
 * @author : 马晓光
 * @date   : 2019/7/18
 * @description :
 **/
@Slf4j
public class MyRetryerListener implements RetryListener {

    @Override
    public <V> void onRetry(Attempt<V> attempt) {
        log.info("time:{}",attempt.getAttemptNumber());
        log.info("距离第一次重试的延迟,delay=",attempt.getDelaySinceFirstAttempt());
        log.info(",hasException={}",attempt.hasException());
        log.info(",hasResult={}",attempt.hasResult());
        if(attempt.hasException()){
           // log.info(" , cause by:",attempt.getExceptionCause());
            log.info("发生了异常");

        }else{
            log.info(",result:",attempt.getResult());
        }
    }
}
