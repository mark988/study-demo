package com.example.deferredresult.service;

import com.example.deferredresult.entity.User;
import com.example.deferredresult.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper  userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addUser(User user) {
        int c = userMapper.insert(user);
        if(c!=1){
            return "fail";
        }
        log.info(" start 即将返回，发生异常 ");
        double x = 1/0;
        log.info(" end 即将返回，发生异常 ");
        return "success";
    }
}
