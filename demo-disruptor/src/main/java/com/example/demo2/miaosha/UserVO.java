package com.example.demo2.miaosha;

import lombok.Data;

@Data
public class UserVO {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private UserMapper userMapper;
}
