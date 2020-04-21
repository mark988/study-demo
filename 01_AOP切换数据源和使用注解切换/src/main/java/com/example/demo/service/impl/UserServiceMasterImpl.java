package com.example.demo.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserMasterService;
import org.springframework.stereotype.Service;

@Service
@DS("master")
public class UserServiceMasterImpl extends ServiceImpl<UserMapper, User> implements UserMasterService {

  @Override
  public void addUser(User user) {
    baseMapper.insert(user);
  }
}
