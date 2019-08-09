package com.example.demo;

import com.example.demo.client.NettyClient;
import com.example.demo.server.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication  implements CommandLineRunner {

    @Autowired
    NettyServer nettyServer;

    /*@Autowired
    NettyClient nettyClient;
*/
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        nettyServer.start();
       // new  NettyClient().start();
      //  new  NettyClient().start();
    }
}
