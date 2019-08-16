package com.example.redissubscribe;

import com.example.redissubscribe.component.TopicMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @author : 马晓光
 * @date   : 2019/8/13
 * @description :
 **/
@RestController("/test")
public class TestController {


    @RequestMapping("/a")
    public String test(){

        return "ok";
    }
}
