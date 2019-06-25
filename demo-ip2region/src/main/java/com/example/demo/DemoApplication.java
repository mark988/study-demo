package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        if(args.length!=1){
            System.out.println("参数不对");
            return;
        }
        //System.out.println(args[0]+","+args[1]);
        String info =  IPUtil.getCityInfo(args[0]);
        System.out.println(info);
        SpringApplication.run(DemoApplication.class, args);
    }

}
