package com.example.deferredresult;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.deferredresult.mapper")
@SpringBootApplication
public class DeferredresultApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeferredresultApplication.class, args);
    }

}
