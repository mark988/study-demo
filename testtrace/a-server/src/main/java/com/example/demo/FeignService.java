package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "b-server",fallback = FeignFallBack.class)
public interface FeignService {
    @RequestMapping(value = "/hello", method= RequestMethod.GET)
    String hello(@RequestParam("name") String name) ;
}
