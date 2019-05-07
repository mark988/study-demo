package com.example.bserver.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "c-server",fallback = FeignFallBack.class)
public interface FeignServiceI {
    @RequestMapping(value = "/c-hello", method= RequestMethod.GET)
    String ctest(@RequestParam("name") String name) ;
}
