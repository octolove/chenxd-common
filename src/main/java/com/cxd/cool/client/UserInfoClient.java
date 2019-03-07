package com.cxd.cool.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cxd.cool.domain.Address;

@FeignClient(configuration = { FeignConfig.class },
    name = "userInfo.center",
    url = "http://localhost:9001/",
    fallbackFactory = UserInfoFallbackFactory.class)
public interface UserInfoClient {

    @RequestMapping(value = "/member22/test",
        method = RequestMethod.POST)
    Address test();

}
