package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/***
 * @author : 马晓光
 * @date   : 2019/6/14
 * @description :
 **/
@TableName(value = "student")
@Data
public class Student {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String  name;
}
