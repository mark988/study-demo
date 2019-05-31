package com.example.demo.strategy;

import java.util.HashMap;
import java.util.Map;

/***
 * @author : 马晓光
 * @date   : 2019/5/24
 * @description :
 **/
public class StrategyFactory {
    private static StrategyFactory factory = new StrategyFactory();
    private StrategyFactory(){
    }
    private static Map strategyMap = new HashMap<>();
    static{
        strategyMap.put(RechargeTypeEnum.E_BANK.value(), new EBankStrategy());
        strategyMap.put(RechargeTypeEnum.BUSI_ACCOUNTS.value(), new BusiAcctStrategy());
        strategyMap.put(RechargeTypeEnum.MOBILE.value(), new MobileStrategy());
        strategyMap.put(RechargeTypeEnum.CARD_RECHARGE.value(), new CardStrategy());
    }
    public Strategy creator(Integer type){
        return (Strategy)strategyMap.get(type);
    }
    public static StrategyFactory getInstance(){
        return factory;
    }
}
