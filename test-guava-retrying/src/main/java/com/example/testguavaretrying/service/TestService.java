package com.example.testguavaretrying.service;

import java.util.List;

public interface TestService {
    List<String> query() throws InterruptedException;
}
