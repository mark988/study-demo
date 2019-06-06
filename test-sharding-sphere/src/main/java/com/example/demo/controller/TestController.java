package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * @author : 马晓光
 * @date   : 2019/6/5
 * @description :
 **/

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public boolean saveUser(User user){
       return userService.saveUser(user);
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.getUserList();
    }
}
