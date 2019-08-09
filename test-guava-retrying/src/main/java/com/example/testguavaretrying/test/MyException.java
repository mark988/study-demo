package com.example.testguavaretrying.test;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.Callable;

/***
 * @author : 马晓光
 * @date   : 2019/7/19
 * @description :
 **/
@Slf4j
public class MyException {

     static Callable<Void> runtimeExceptionTask = new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            log.info("runtime was called.");
            throw new NullPointerException("runtime");
        }
    };

    private static Callable<Void> checkedExceptionTask = new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            log.info("checked was called.");
            throw new IOException("checked");
        }
    };

    private static Callable<Void> errorTask = new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            log.info("error was called.");
            throw new ThreadDeath();
        }
    };
}
