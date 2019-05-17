package com.example.demo.controller;

import com.example.demo.server.ConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ConsumerServiceImpl consumerService;



    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String  test(){
       return  consumerService.sayHello("aaaaaaaaaaaaaaaaaa");
    }
}
