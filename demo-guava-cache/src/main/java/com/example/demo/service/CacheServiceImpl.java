package com.example.demo.service;

import com.example.demo.dao.StudentMapper;
import com.example.demo.domain.Student;
import com.google.common.cache.*;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/***
 * @author : 马晓光
 * @date   : 2019/6/20
 * @description :
 **/
@Slf4j
@Service
public class CacheServiceImpl implements CacheService {

     @Autowired
     private StudentMapper studentMapper;

     private   RemovalListener<String, Object> removalListener =null;
     private   LoadingCache<String,Object> loadingCache =null;

     @PostConstruct
     public void init(){
         log.info("初始化缓存服务");
         removalListener = new RemovalListener<String, Object>(){
             @Override
             public void onRemoval(RemovalNotification<String, Object> notification) {
                 log.info(notification.getKey()+"被移除"+" 移除原因:"+notification.getCause());
                 //可以在监听器中获取key,value,和删除原因
                 notification.getValue();
                 notification.getCause();//EXPLICIT、REPLACED、COLLECTED、EXPIRED、SIZE
             }};

        //指定一个如果数据不存在获取数据的方法
        CacheLoader<String, Object> cacheLoader = new CacheLoader<String, Object>() {
            @Override
            public Object load(String key) throws Exception {
                //mysql操作
                log.info("LoadingCache---------------ing...(2s)");
                List<Student> studentList=(List<Student>)studentMapper.selectList(null);
                log.info("LoadingCache-------------缓存成功");
                return studentList;
            }

        };

        loadingCache = CacheBuilder.newBuilder().
                        expireAfterAccess(2, TimeUnit.SECONDS).
                        expireAfterWrite(2, TimeUnit.SECONDS).
                        refreshAfterWrite(2,TimeUnit.SECONDS).
                        weakKeys().
                        maximumSize(1).
                        removalListener(removalListener).
                        build(cacheLoader);
    }

    @Override
    public void save(String key, Object o) {
         loadingCache.put(key,o);
    }

    @Override
    public Object get(String key) throws ExecutionException {
       return   loadingCache.get(key);
    }
}
