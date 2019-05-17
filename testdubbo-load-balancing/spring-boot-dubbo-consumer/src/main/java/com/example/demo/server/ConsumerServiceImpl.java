package com.example.demo.server;


import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;


@Service
public class ConsumerServiceImpl{

    //设置重试参数3,如果重试3次仍然失败则换另外的服务
    @Reference(timeout=5000)
    private  ProviderServiceI providerServiceI;

    public String sayHello(String msg) {
        return providerServiceI.sayHello(msg);
    }

}
