package com.example.demo.service;

import com.example.demo.dao.StudentMapper;
import com.example.demo.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/***
 * @author : 马晓光
 * @date   : 2019/6/14
 * @description :
 **/
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> selectAll() {
         List<Student> map = (List<Student>) studentMapper.selectList(null);
         return map;
    }
}
