package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 * @author : 马晓光
 * @date   : 2019/6/1
 * @description :
 **/
@Slf4j
@Controller
public class TestController {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(Map<String,String> map){

        map.put("google","www.google.com");
        map.put("baidu","www.baidu.com");
        return "index?uid=1";
    }
}
