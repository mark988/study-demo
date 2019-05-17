package com.example.demo.server;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Service  //(loadbalance = "consistenthash")
public class ProviderServiceImpl implements ProviderServiceI {



    @Value("${server.port}")
    private String port ;

    @Override
    public String sayHello(String msg) {
        log.info("开始执行A服务中...");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("执行A服务结束");
        String ret = "远程服务port:"+port;
        return ret;
    }

}
