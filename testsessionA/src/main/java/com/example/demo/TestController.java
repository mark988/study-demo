package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by 马晓光 on 2019/4/24.
 */

@Slf4j
@RestController
public class TestController  {
    @RequestMapping("/set-session")
    public Object setSession(String sessionValue,HttpSession httpSession){
        httpSession.setAttribute("sessionVal",sessionValue);
        return sessionValue;
    }

    @RequestMapping("/get-session")
    public Object getSession(HttpSession httpSession){
        log.info("A server");
        Object o = httpSession.getAttribute("sessionVal");
        return o;
    }
}
