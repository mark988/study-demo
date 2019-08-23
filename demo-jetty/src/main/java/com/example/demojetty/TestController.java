package com.example.demojetty;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/***
 * @author : 马晓光
 * @date   : 2019/8/23
 * @description :
 **/
@RestController
public class TestController {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return  "Hello world";
    }
}
