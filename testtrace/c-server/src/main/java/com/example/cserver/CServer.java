package com.example.cserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class CServer {

    public static void main(String[] args) {
        SpringApplication.run(CServer.class, args);
    }

}
