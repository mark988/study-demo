package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 实现文件上传
     * */
    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("fileName") MultipartFile file){
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        return "true";
    }

    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    public String userAdd(@RequestBody User user){
        log.info("name:{},age:{}",user.getName(),user.getAge());
        return "ok";
    }
}
