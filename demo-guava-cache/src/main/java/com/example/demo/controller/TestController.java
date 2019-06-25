package com.example.demo.controller;

import com.example.demo.domain.Student;
import com.example.demo.service.CacheService;
import com.example.demo.service.StudentService;
import com.sun.xml.internal.ws.api.pipe.ServerTubeAssemblerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * @author : 马晓光
 * @date   : 2019/6/14
 * @description :
 **/
@Slf4j
@RequestMapping("/test")
@RestController()
public class TestController  {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Student> list(){
        return studentService.selectAll();
    }

    @RequestMapping(value = "/list-cache",method = RequestMethod.GET)
    public List<Student> listCache() throws Exception{
        return (List<Student>)cacheService.get("testKey");
    }
}
