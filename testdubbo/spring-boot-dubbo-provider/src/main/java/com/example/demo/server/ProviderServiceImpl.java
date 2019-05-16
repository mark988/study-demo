package com.example.demo.server;

import com.alibaba.dubbo.config.annotation.Service;


@Service
public class ProviderServiceImpl implements ProviderServiceI {

    @Override
    public String sayHello(String msg) {
        String ret = "远程服务已经调用到:"+msg;
        return ret;
    }

}
