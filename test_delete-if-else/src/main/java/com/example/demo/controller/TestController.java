package com.example.demo.controller;

import com.example.demo.Strategy3.StrategyContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/***
 * @author : 马晓光
 * @date   : 2019/5/28
 * @description :
 **/
@Slf4j
@RestController
public class TestController {

    @Autowired
    private StrategyContext strategyContext;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public BigDecimal test(@RequestParam String leve){
        return strategyContext.calculatePrice(leve);
    }
}
