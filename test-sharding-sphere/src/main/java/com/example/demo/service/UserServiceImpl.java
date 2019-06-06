package com.example.demo.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * @author : 马晓光
 * @date   : 2019/6/5
 * @description :
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean saveUser(User user) {
        return super.save(user);
    }

    @Override
    public List<User> getUserList() {
        return baseMapper.selectList(Wrappers.<User>lambdaQuery());
    }
}
