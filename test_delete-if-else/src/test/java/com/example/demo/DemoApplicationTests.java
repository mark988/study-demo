package com.example.demo;

import com.example.demo.strategy.RechargeTypeEnum;
import com.example.demo.strategy.StrategyContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {

        StrategyContext context = new StrategyContext();
        // 网银充值100 需要付多少
        Double money = context.calRecharge(100D,
                RechargeTypeEnum.E_BANK.value());
        System.out.println(money);

        // 商户账户充值100 需要付多少
        Double money2 = context.calRecharge(100D,
                RechargeTypeEnum.BUSI_ACCOUNTS.value());
        System.out.println(money2);

        // 手机充值100 需要付多少
        Double money3 = context.calRecharge(100D,
                RechargeTypeEnum.MOBILE.value());
        System.out.println(money3);

        // 充值卡充值100 需要付多少
        Double money4 = context.calRecharge(100D,
                RechargeTypeEnum.CARD_RECHARGE.value());
        System.out.println(money4);
    }

}
