package com.example.demo.entity;

/***
 * @author : 马晓光
 * @date   : 2019/5/18
 * @description :
 **/

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}