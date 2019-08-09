package com.example.testguavaretrying.controller;

import com.example.testguavaretrying.callable.TaskCallable;
import com.example.testguavaretrying.component.ProductRetryerBuilder;
import com.example.testguavaretrying.listener.MyRetryerListener;
import com.example.testguavaretrying.service.CacheService;
import com.example.testguavaretrying.service.TestService;
import com.github.rholder.retry.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/***
 * @author : 马晓光
 * @date   : 2019/7/18
 * @description :
 **/
@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {

   /* @Autowired
    TestService testService;*/

    @Autowired
    CacheService cacheService;

    @Autowired
    TestService testService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<String> test() throws Exception{
        log.info("开始进入controller");
        List<String> list  =  testService.query();
        return list;
    }
}
