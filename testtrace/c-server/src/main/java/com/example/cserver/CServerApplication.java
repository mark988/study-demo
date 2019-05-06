package com.example.cserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class CServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CServerApplication.class, args);
    }

}
