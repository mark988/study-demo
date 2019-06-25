package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/***
 * @author : 马晓光
 * @date   : 2019/6/13
 * @description :
 **/
@Slf4j
@RestController
public class TestController {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        log.info("request here");
        return  "success";
    }
}
