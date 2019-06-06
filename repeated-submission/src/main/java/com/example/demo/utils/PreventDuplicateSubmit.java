package com.example.demo.utils;

import java.lang.annotation.*;

/***
 * @author : 马晓光
 * @date   : 2019/6/2
 * @description :
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PreventDuplicateSubmit {
    String prefix() default "prefix:";
}
