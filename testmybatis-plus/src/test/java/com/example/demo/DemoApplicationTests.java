package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
        System.out.println("-----------------------");
        List<User> userList= userMapper.selectList(null);
        System.out.println("数据库总记录数:"+userList.size());

         for(User u:userList){
             System.out.println(u.getName());
         }
        User u2 = userMapper.getUserTest(1);
        System.out.println(u2.getName());
    }

}
