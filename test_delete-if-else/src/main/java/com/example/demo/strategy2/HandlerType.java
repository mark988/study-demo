package com.example.demo.strategy2;

import java.lang.annotation.*;

/***
 * @author : 马晓光
 * @date   : 2019/5/25
 * @description :
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited
public @interface HandlerType {
    String value();
}
