package com.example.bserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;



@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class BServer {

    public static void main(String[] args) {
        SpringApplication.run(BServer.class, args);
    }
}