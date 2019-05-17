package com.example.demo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDaoI {
    @Override
    public void connectionDB() {
        System.out.println("connection db succ");
    }
}
