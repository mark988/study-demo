package com.example.demo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan({"com.example.demo.mapper"})
@EnableDubbo
@SpringBootApplication
public class ProviderA {

    public static void main(String[] args) {
        SpringApplication.run(ProviderA.class, args);
    }

}
