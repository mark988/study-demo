package demoencryptbody.demoencryptbody;

import cn.shuibo.annotation.Decrypt;
import cn.shuibo.annotation.Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/***
 * @author : Administrator
 * @date   : 2019/9/10 0010
 * @description :
 **/
@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {

    /**
     *
     * 返回值加密
     *
     * **/
    @Encrypt
    @GetMapping("/encryption")
    public String encryption(){
        return "Hello world";
    }
    /**
     * 参数解密
     *
     * **/
    @Decrypt
    @GetMapping("/decryption")
    public String Decryption(@RequestParam String string){
        log.info(string);
        return string;
    }
}
