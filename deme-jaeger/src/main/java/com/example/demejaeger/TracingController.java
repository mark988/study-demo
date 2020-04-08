package com.example.demejaeger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TracingController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private int port;

    @RequestMapping("/tracing")
    public String tracing() throws InterruptedException {
        Thread.sleep(100);
        return "tracing";
    }

    @RequestMapping("/open")
    public String open() throws InterruptedException {
        ResponseEntity<String> response =
                restTemplate.getForEntity("http://localhost:" + port + "/tracing",
                        String.class);
        Thread.sleep(200);
        return "open " + response.getBody();
    }
}
