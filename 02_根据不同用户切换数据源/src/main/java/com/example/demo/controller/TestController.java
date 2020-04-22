package com.example.demo.controller;

import com.example.demo.aop.EagleEye;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    /**
     * 方法上没有使用DS注解，所以判断过来的方式是否带有list等，查询直接走slave
     * @return
     */
    @EagleEye
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List test2(){
        return userService.listUser();
    }
}
