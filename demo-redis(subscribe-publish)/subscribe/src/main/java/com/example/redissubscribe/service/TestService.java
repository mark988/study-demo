package com.example.redissubscribe.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/***
 * @author : 马晓光
 * @date   : 2019/8/13
 * @description :
 **/
@Slf4j
@Service
public class TestService {


    public String saveObject(){
        log.info("保存对象成功");
        return "success";
    }
}
