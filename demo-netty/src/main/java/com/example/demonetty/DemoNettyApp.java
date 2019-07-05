package com.example.demonetty;

import com.example.demonetty.client.NettyClient;
import com.example.demonetty.server.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoNettyApp implements CommandLineRunner {

    @Autowired
    private NettyServer nettyServer;

    @Autowired
    private NettyClient nettyClient;

    public static void main(String[] args) {
        SpringApplication.run(DemoNettyApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        nettyServer.start();
        nettyClient.start();
    }
}
