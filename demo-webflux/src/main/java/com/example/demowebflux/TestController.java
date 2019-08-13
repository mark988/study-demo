package com.example.demowebflux;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/***
 * @author : 马晓光
 * @date   : 2019/8/9
 * @description :
 **/
@Slf4j
@RequestMapping
@RestController
public class TestController {

    @RequestMapping("/test")
    public  String test() throws Exception{
        log.info("Starting NON-BLOCKING Controller!");
        Mono<String>  stringMono= WebClient.create().get()
                                                    .uri("http://localhost:8080/test3")
                                                    .retrieve().bodyToMono(String.class);
        log.info("End NON-BLOCKING Controller!");
        return stringMono.block();
    }
    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public  String test2() throws Exception{
        log.info("----------------- starting BLOCKING Controller!");
        RestTemplate template=new RestTemplate();
        String  result= template.getForObject("http://localhost:8080/test3",String.class);
        Thread.sleep(10000L);
        log.info("----------------- end BLOCKING Controller!");
        return result;
    }

    @RequestMapping(value = "/test3",method = RequestMethod.GET)
    public  String test3() throws Exception{
        log.info("*************** starting request ***********!");
        Thread.sleep(10000L);
        log.info("*************** end request ***********!");
        return "OK";
    }

}
