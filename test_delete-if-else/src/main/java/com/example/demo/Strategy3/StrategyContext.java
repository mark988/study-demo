package com.example.demo.Strategy3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/***
 * @author : 马晓光
 * @date   : 2019/5/28
 * @description :
 **/
@Service
public class StrategyContext {
    private final Map<String,Strategy> stringStrategyMap=new ConcurrentHashMap<>();

    @Autowired
    public StrategyContext(Map<String,Strategy> stringStrategyMap){
        this.stringStrategyMap.clear();
         stringStrategyMap.forEach((k,v)->this.stringStrategyMap.put(k,v));
    }
    public BigDecimal calculatePrice(String memberLeve){
        return stringStrategyMap.get(memberLeve).calculatePrice();
    }
}
