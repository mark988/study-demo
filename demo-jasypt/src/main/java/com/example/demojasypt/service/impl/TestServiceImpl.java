package com.example.demojasypt.service.impl;

import com.example.demojasypt.dao.TestMapper;
import com.example.demojasypt.domain.Test;
import com.example.demojasypt.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/***
 * @author : 马晓光
 * @date   : 2019/6/13
 * @description :
 **/
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public int saveTest(Test test) {
        return testMapper.insert(test);
    }
}
