package com.example.demo.controller;

import com.example.demo.aop.EagleEye;
import com.example.demo.entity.User;
import com.example.demo.service.UserSlaveService;
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
    private UserSlaveService userService;

    @Autowired
    private UserService userService2;


    /**
     * 方法上使用了注解DS ，所以DS注解为准
     * @return
     */
    @RequestMapping(value = "/slave",method = RequestMethod.GET)
    public  String  test(){
        User u = new User();
        u.setName("Tom online");
        u.setAge((int) (Math.random()*(30)));
        userService.addUser(u);
        return "操作slave数据库";
    }

    /**
     * 方法上没有使用DS注解，所以判断过来的方式是否带有list等，查询直接走slave
     * @return
     */
    @EagleEye
    @RequestMapping(value = "/slave2",method = RequestMethod.GET)
    public List test2(){
        return userService2.listUser();
    }
}
