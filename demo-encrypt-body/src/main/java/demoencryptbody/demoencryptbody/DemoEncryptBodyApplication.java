package demoencryptbody.demoencryptbody;

import cn.shuibo.annotation.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSecurity
@SpringBootApplication
public class DemoEncryptBodyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoEncryptBodyApplication.class, args);
    }

}
