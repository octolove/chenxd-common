package com.cxd.cool.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cxd.cool.domain.Address;

@FeignClient(configuration = { FeignConfig.class },
    name = "chenxd-common",
    url = "",
    fallbackFactory = UserInfoFallbackFactory.class)
public interface AddressClient {

    @RequestMapping(value = "/member22/address",
        method = RequestMethod.POST)
    Address add();

}
