package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    boolean saveUser(User user);
    List<User> getUserList();
}
