package com.example.demo.strategy2;

import com.example.demo.OrderDTO;

import java.util.Map;

/***
 * @author : 马晓光
 * @date   : 2019/5/24
 * @description :
 **/
public class HandlerContext {
    private Map<String,Class> handlerMap;
    public HandlerContext(Map<String,Class> handlerMap){
         this.handlerMap = handlerMap;
    }
    public AbstractHandler getInstance(String type){
        Class clazz =handlerMap.get(type);
        if(clazz == null){
            throw new IllegalArgumentException("not found handler for type:"+type);
        }
        return (AbstractHandler) BeanTool.getBean(clazz);
    }
}
