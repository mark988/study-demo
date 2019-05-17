package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/***
 * @author : 马晓光
 * @date   : 2019/5/18
 * @description :
 **/
public interface UserMapper extends BaseMapper<User> {
   // User selectOneTest();

   /* @Lang(MyLanguageDriver.class)
    @Select("SELECT * FROM user")
    List<User> selectBlog();
*/
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserTest(@Param("id") Integer id);
}