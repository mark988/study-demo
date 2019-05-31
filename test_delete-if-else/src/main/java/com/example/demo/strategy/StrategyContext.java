package com.example.demo.strategy;

/***
 * @author : 马晓光
 * @date   : 2019/5/24
 * @description :
 **/
public class StrategyContext {
    private Strategy strategy;

    public Double calRecharge(Double charge, Integer type) {
        strategy = StrategyFactory.getInstance().creator(type);
        return strategy.calRecharge(charge, RechargeTypeEnum.valueOf(type));
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
