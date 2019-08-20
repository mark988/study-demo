package com.example.demojasypt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.demojasypt.dao")
@SpringBootApplication
public class DemoJasyptApp {

    public static void main(String[] args) {
        SpringApplication.run(DemoJasyptApp.class, args);
    }

}
