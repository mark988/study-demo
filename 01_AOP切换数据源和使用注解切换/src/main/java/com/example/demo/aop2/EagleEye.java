package com.example.demo.aop2;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface EagleEye {
    String desc() default "";
}
