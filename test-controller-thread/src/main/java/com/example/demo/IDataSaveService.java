package com.example.demo;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public interface IDataSaveService {
    void saveToMem(String uid, Object o);
    void saveToMem2(String uid, Object o);
    Object getFromMem(String uid);

    Integer sizeConcurrentMap();
    Integer sizeHashMap();
}
