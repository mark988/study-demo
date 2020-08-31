package com.example.demo;

import java.security.Principal;

/**
 *
 * @ClassName:
 * @Description: 客户端用户对象
 * @author
 * @date 2020年8月29日 下午3:02:54
 */
public final class CustomerUser implements Principal {

    private final String name;

    public CustomerUser(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
