package com.example.deferredresult.v2;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 队列监听器，初始化启动所有监听任务
 *
 * @author Logen
 *
 */
@Slf4j
@Component
public class QueueListener {

    @Autowired
    private OrderTask orderTask;

    /**
     * 初始化时启动监听请求队列
     */
    @PostConstruct
    public void init() {
        log.info(" 订单启动");
        orderTask.start();
    }

    /**
     * 销毁容器时停止监听任务
     */
    @PreDestroy
    public void destory() {
        log.info(" 订单任务销毁");
        orderTask.setRunning(false);
    }

}
