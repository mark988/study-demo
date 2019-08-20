package com.example.demojasypt.controller;

import com.example.demojasypt.domain.Test;
import com.example.demojasypt.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/save/{name}",method = RequestMethod.GET)
    public String saveTest(@PathVariable String name){
        Test test =new Test();
        test.setName(name);
        testService.saveTest(test);
        return "success";
    }
}
