package com.example.deferredresult;


import java.util.HashMap;

public class R<T> extends HashMap<String, Object> {
        private static final long serialVersionUID = 1L;

        public R(int code,String msg) {
            put("code", code);
            put("msg", msg);
            put("data", null);
            put("error",false);
        }

        public static R fail() {

            return new R(-1,"fail");
        }

        public static R fail(String msg) {
            return fail(-1, msg);
        }

        public static R fail(int code, String msg) {
            R r = new R(code,msg);
            r.put("error",true);
            r.put("data",null);
            return r;
        }

        public static <T> R<T> fail(T data) {
            R r = new R(-1,"fail");
            r.put("data",data);
            r.put("error",true);
            return r;
        }


        public static <T> R<T> fail(int code,String msg,T data) {
            R r = new R(code,msg);
            r.put("data",data);
            r.put("error",true);
            return r;
        }



        public static R ok() {

            return new R(0,"success");
        }



        public static R ok(String msg) {
            R r = new R(0,msg);
            r.put("data",msg);
            r.put("error",false);

            return r;
        }

        public static R ok(int code, String msg) {
            R r = new R(code,msg);
            r.put("code", code);
            r.put("msg", msg);
            r.put("data", null);
            r.put("error",false);

            return r;
        }


        public static <T> R<T> ok(T data) {
            R r = new R(0,"success");
            r.put("data",data);
            r.put("error",false);
            return r;
        }


        public static <T> R<T> ok(int code,String msg,T data) {
            R r = new R(code,msg);
            r.put("data",data);
            r.put("error",false);
            return r;
        }


        @Override
        public R put(String key, Object value) {
            super.put(key, value);
            return this;
        }


    }
