package com.example.demo.entity;


import  com.baomidou.mybatisplus.annotation.TableName;
import  com.baomidou.mybatisplus.extension.activerecord.Model;
import  groovy.transform.EqualsAndHashCode;
import  lombok.Data;
import  lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper =true)
@Accessors (chain =true)
@TableName ( "user")
public class  User  extends  Model <User> {
    /**
     * 主键Id
     */
    private int  id;
    /**
     * 名称
     */
    private  String  name;
    /**
     * 年龄
     */
    private int  age;
}
