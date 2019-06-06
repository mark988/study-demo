package com.example.demo;

import cn.miludeer.jsoncode.JsonCode;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/***
 * @author : 马晓光
 * @date   : 2019/6/5
 * @description :
 **/
public class TestJsonCode {
    static   String  str="{" +
            "    \"json\": {" +
            "        \"a\": {" +
            "            \"www\": \"ff\"," +
            "            \"rrr\": [\"v1\", \"v2\"]" +
            "        }," +
            "        \"b\": {" +
            "            \"www\": \"ttt\"," +
            "            \"rrr\": [\"v1\", \"v2\"]" +
            "        }" +
            "    }" +
            "}";
    public static String userFastJson(){
        JSONObject ob = JSON.parseObject(str).getJSONObject("json").getJSONObject("b");
        return ob.getString("www");
    }
    public static String userJsonCode(){
        String ret = JsonCode.getValue(str,"$.b.www");
        return ret;
    }

    public static void main(String[] args){
        long time1=System.currentTimeMillis();
        for(int i =0;i<1000000;i++){
            userJsonCode();
        }
        long time2=System.currentTimeMillis();
        for(int i=0;i<1000000;i++){
            userFastJson();
        }
        long time3=System.currentTimeMillis();

        System.out.println("fastjson 消耗时间:"+(time3-time2)/1000);
        System.out.println("json code 消耗时间:"+(time2-time1)/1000);
    }
}
