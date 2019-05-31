package com.example.demo.strategy2;

import com.example.demo.OrderDTO;
import org.springframework.stereotype.Component;

/***
 * @author : 马晓光
 * @date   : 2019/5/25
 * @description :
 **/
@Component
public class PromotionHandler extends AbstractHandler {
    @Override
    public String handle(OrderDTO dto) {
        return "处理促销订单";
    }
}
