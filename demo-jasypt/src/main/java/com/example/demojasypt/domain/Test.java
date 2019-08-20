package com.example.demojasypt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/***
 * @author : 马晓光
 * @date   : 2019/6/13
 * @description : 测试类
 **/

@TableName(value = "t_test")
@Data
public class Test {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
}
