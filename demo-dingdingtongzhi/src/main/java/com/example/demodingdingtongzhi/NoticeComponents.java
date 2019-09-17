package com.example.demodingdingtongzhi;

import org.springframework.stereotype.Component;
import com.kuding.anno.ExceptionListener;
import javax.validation.constraints.Size;


@Component
@ExceptionListener
public class NoticeComponents {

    public void someMethod(String name) {
        System.out.println("这是一个参数：" + name);
        throw new NullPointerException("第一个异常");
    }

    public void anotherMethod(String name, int age) {
        System.out.println("这又是一个参数" + age);
        throw new IllegalArgumentException(name + ":" + age);
    }
}