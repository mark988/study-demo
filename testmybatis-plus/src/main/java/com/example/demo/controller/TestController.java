package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * @author : 马晓光
 * @date   : 2019/5/18
 * @description :
 **/
@Slf4j
@RestController
public class TestController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    private String test(){
        List<User> userList= userMapper.selectList(null);
        return "OK";
    }
}
