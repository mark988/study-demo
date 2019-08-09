package com.example.testguavaretrying.service;

import java.util.concurrent.ExecutionException;

public interface CacheService {

    void save(String key, Object o);
    Object  get(String key) throws ExecutionException;
}
