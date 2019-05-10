package com.example.demo.logbackExt.convert;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.example.demo.logbackExt.utils.IncrementIdContext;


public class IncrementIdConvert extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        return IncrementIdContext.get();
    }
}