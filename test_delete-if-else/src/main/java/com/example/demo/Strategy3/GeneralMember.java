package com.example.demo.Strategy3;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/***
 * @author : 马晓光
 * @date   : 2019/5/27
 * @description :
 **/
@Service
public class GeneralMember implements Strategy {
    @Override
    public BigDecimal calculatePrice() {
        return new BigDecimal(100);
    }
}
