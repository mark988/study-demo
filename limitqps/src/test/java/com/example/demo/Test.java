package com.example.demo;

import net.bytebuddy.build.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by 马晓光 on 2019/5/3.
 */
public class Test {

    List<Integer> list=new ArrayList<>();

    public Test(){
        for(int i=0;i<10;i++){
            list.add(i);
        }
    }

    @org.junit.Test
    public void test(){
        //long c = list.stream().count();
        //System.out.println(c);
         Stream<Integer>  integerStream= list.stream().filter((e)->e.intValue()>2).limit(3);

         integerStream.forEach(System.out::println);
    }
}
