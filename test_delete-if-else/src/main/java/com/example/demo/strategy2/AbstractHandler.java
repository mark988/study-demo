package com.example.demo.strategy2;

import com.example.demo.OrderDTO;

/***
 * @author : 马晓光
 * @date   : 2019/5/24
 * @description :
 **/
public abstract class AbstractHandler {
    abstract  public String handle(OrderDTO dto);
}
