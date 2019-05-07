package com.example.cserver.config;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/***
 * @apiNote 获取当前机器的IP地址或者主机名字
 * @author xiaomage
 * @date 2019/5/7
 * **/

@Slf4j
public class IPLogConfig extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
