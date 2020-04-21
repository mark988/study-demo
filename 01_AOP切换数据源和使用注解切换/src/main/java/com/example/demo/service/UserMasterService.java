package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;

public interface UserMasterService extends IService<User> {

  void addUser(User user);

}
