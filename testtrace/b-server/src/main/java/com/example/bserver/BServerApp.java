package com.example.bserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BServerApp {

    public static void main(String[] args) {
        SpringApplication.run(BServerApp.class, args);
    }

}
