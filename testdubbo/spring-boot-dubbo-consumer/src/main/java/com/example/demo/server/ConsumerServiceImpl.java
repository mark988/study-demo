package com.example.demo.server;


import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;


@Service
public class ConsumerServiceImpl{


    @Reference
    private  ProviderServiceI providerServiceI;

    public String sayHello(String msg) {
        return providerServiceI.sayHello(msg);
    }

}
