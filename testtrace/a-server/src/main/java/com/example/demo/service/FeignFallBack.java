package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class FeignFallBack implements FeignService {
    @Override
    public String hello(String name) {
        return "Erro";
    }
}
