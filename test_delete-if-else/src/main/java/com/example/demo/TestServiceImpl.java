package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @author : 马晓光
 * @date   : 2019/5/24
 * @description :
 **/
@Service
public class TestServiceImpl implements TestServiceI {



    @Override
    public void queryName(Integer type) {
        if(type == 1){
            System.out.println(" type="+type);
        }else if(type == 2){
            System.out.println("type ="+type);
        }else if(type == 3){
            System.out.println("type ="+type);
        }
    }
}
