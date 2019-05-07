package com.example.bserver.service;

import org.springframework.stereotype.Service;

@Service
public class FeignFallBack implements FeignServiceI {
    @Override
    public String ctest(String name) {
        return "Erro,调用C服务没有成功.";
    }
}
