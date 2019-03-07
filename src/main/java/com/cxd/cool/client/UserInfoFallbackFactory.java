package com.cxd.cool.client;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class UserInfoFallbackFactory implements FallbackFactory<UserInfoClient> {

    @Override
    public UserInfoClient create(Throwable cause) {
        System.out.println("----------------->UserInfoClient");
        return null;
    }
}
