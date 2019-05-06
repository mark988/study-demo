package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ATestController {

    @Autowired
    private  FeignService feignService;



    @RequestMapping("/test")
    public String test(@RequestParam String name){

        String result=feignService.hello(name);
        return result;
    }
}
