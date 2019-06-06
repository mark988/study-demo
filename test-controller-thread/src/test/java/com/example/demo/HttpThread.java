package com.example.demo;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/***
 * @author : 马晓光
 * @date   : 2019/6/1
 * @description :
 **/
public class HttpThread {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable(){
                public void run(){
                    CloseableHttpClient httpclient = HttpClients.createDefault();
                    HttpGet httpGet = new HttpGet("http://localhost:8080/test1");
                    System.out.println(Thread.currentThread().getName());
                    httpGet.setHeader("Thread", Thread.currentThread().getName());
                    try {
                        httpclient.execute(httpGet);
                    } catch (ClientProtocolException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
