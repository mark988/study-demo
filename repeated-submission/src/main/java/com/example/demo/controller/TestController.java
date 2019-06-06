package com.example.demo.controller;


import com.example.demo.utils.PreventDuplicateSubmit;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {

    /**
     * 防止重复提交
     *
     * **/
    @PreventDuplicateSubmit
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity getUserById(@RequestParam Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body("Hello world");
    }
}