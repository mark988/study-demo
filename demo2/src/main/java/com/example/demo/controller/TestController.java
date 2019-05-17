package com.example.demo.controller;

import com.example.demo.service.TestServerI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestServerI testServerI;

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String test(){
        testServerI.test();
        return "Hello ";
    }
}
