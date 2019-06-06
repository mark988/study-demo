package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/***
 * @author : 马晓光
 * @date   : 2019/6/1
 * @description :
 **/
@Slf4j
@Service
public class DataSaveServiceImpl implements IDataSaveService {

    private static ConcurrentMap<String, Object> concurrentMap = new ConcurrentHashMap<>();
    private static Map<String, Object> hashMap = new HashMap<>();

    @Override
    public  void  saveToMem(String uid, Object newValue) {

        Object oldValue;
        while (true) {
            oldValue = concurrentMap.get(uid);
            if (oldValue == null) {
                //newValue = 1L;
                //已经有key了就返回放入的值，否则返回空
                if (concurrentMap.putIfAbsent(uid, newValue) == null) {
                    break;
                }
            } else {
                //newValue = oldValue + 1;
                //值替换，每次替换时都会比较上面拿到oldValue是否就是当前map里面的值，是才替换，否则继续获取
                if (concurrentMap.replace(uid, oldValue, newValue)) {
                    break;
                }
            }
        }
    }

    @Override
    public void saveToMem2(String uid, Object o) {
        hashMap.put(uid,o);
    }

    @Override
    public Object getFromMem(String uid) {
        return concurrentMap.get(uid);
    }

    @Override
    public Integer sizeConcurrentMap() {
        return concurrentMap.size();
    }

    @Override
    public Integer sizeHashMap() {
        return hashMap.size();
    }
}
