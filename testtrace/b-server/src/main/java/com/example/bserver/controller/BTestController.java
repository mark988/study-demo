package com.example.bserver;

import com.example.bserver.service.FeignServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BTestController {


    @Value("${server.port}")
    String PROT;
    @Value("${spring.application.name}")
    String SERVER_NAME;

    @Autowired
    private FeignServiceI feignServiceI;

    @RequestMapping("/hello")
    public String btest(@RequestParam String name){
        log.info("我是远程服务b-server");

        log.info("开始调用远程服务C-server");
        String s= feignServiceI.ctest(name);
        log.info("结束调用远程服务C-server");
        return s;
    }
}
