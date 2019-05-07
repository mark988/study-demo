package com.example.cserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CTestController {


    @Value("${server.port}")
    String PROT;
    @Value("${spring.application.name}")
    String SERVER_NAME;

    @RequestMapping("/hello")
    public String btest(@RequestParam String name){
        return "I am  "+SERVER_NAME+" , port is "+PROT;
    }
}
