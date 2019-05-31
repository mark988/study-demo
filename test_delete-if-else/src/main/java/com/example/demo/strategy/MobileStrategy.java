package com.example.demo.strategy;

/***
 * @author : 马晓光
 * @date   : 2019/5/24
 * @description :
 **/
public class MobileStrategy implements Strategy {
    @Override
    public Double calRecharge(Double charge, RechargeTypeEnum type) {
        return charge;
    }
}
