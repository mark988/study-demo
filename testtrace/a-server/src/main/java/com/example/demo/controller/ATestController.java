package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ATestController {

    @Autowired
    private  FeignService feignService;



    @RequestMapping("/test")
    public String test(@RequestParam String name){
        log.info("开始调用本地服务");
        String result=feignService.hello(name);
        log.info("结束调用本地服务");
        return result;
    }
}
