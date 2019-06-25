package com.example.demo;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class DemoApplication {

       ///打开此处支持https
       @Value("${https.port}")
       private Integer port;

       @Value("${https.ssl.key-store-password}")
       private String key_store_password;

       @Value("${https.ssl.key-password}")
       private String key_password;


       @Bean
       public ServletWebServerFactory servletContainer() {
           TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
           // 添加http
           tomcat.addAdditionalTomcatConnectors(createSslConnector());
           return tomcat;
       }
       private Connector createSslConnector() {
           Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
           Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
           try {

               ClassPathResource resource = new ClassPathResource("sample.jks");
               InputStream inputStream = resource.getInputStream();
               File f= new File("src/main/resources/targetFile.tmp");
               FileUtils.copyInputStreamToFile(inputStream, new File("src/main/resources/targetFile.tmp"));
               connector.setScheme("https");
               connector.setSecure(true);
               connector.setPort(port);
               protocol.setSSLEnabled(true);
               protocol.setKeystoreFile(f.getAbsolutePath());
               protocol.setKeystorePass(key_store_password);
               protocol.setKeyPass(key_password);
               return connector;
           }catch (IOException ex) {
               throw new IllegalStateException("can't access keystore: [" + "keystore"
                       + "] or truststore: [" + "keystore" + "]", ex);
           }
       }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
