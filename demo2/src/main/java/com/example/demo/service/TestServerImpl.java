package com.example.demo.service;

import com.example.demo.dao.TestDaoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServerImpl implements TestServerI {

    @Autowired
    private TestDaoI testDaoI;

    @Override
    public void test() {
        testDaoI.connectionDB();
        System.out.println("数据整理 并返回");
    }
}
