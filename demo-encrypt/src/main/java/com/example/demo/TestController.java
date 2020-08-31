package com.example.demo;

import cn.shuibo.annotation.Decrypt;
import cn.shuibo.annotation.Encrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Encrypt
    @GetMapping("/encryption")
    public String  encryption(){
        return "this is OK";
    }

    @Decrypt
    @PostMapping("/decryption")
    public String Decryption(@RequestBody Map<String,String> map){
        return map.toString();
    }

}
