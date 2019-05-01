package com.example.demo;

import cn.yueshutong.springbootstartercurrentlimiting.annotation.CurrentLimiter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 马晓光 on 2019/5/1.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/limit",method = RequestMethod.GET)
    @CurrentLimiter(QPS=100)
    public String test() throws InterruptedException {
        System.out.print("当前时间："+System.currentTimeMillis());
        System.out.printf("thread name :"+Thread.currentThread().getName());
        Thread.sleep(1);
        return "success";
    }
}
