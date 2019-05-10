package com.example.demo.logbackExt.convert;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.example.demo.logbackExt.utils.ThreadUUIDContext;


/**
 * Created by zd.yao on 2017/5/24.
 */
public class ThreadUUIDConvert extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        return ThreadUUIDContext.get();
    }
}