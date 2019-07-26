package com.cxd.cool.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxd.cool.client.AddressClient;
import com.cxd.cool.client.UserInfoClient;
import com.cxd.cool.domain.Address;

@RestController
@RequestMapping(value = "/member")
public class FeignClientAction {

    private Logger logger = LoggerFactory.getLogger(FeignClientAction.class);

    @Autowired
    private UserInfoClient userInfoClient;

    @Autowired
    private AddressClient addressClient;

    @RequestMapping(value = "/test")
    public void testok(@RequestBody Address address) {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>testok" + new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss").format(new Date()));
        
        throw new RuntimeException("33242342");

    }

    @RequestMapping(value = "/feign")
    public Address feign() {
        Address xxx = userInfoClient.test();
        logger.info(">>>>>>>>>>>>>>>>>>>xxx={}", xxx.toString());
        return xxx;
    }

    @RequestMapping(value = "/feignAddress")
    public Address feignAddress() {
        Address xxx = addressClient.add();
        logger.info(">>>>>>>>>>>>>>>>>>>xxx={}", xxx.toString());
        return xxx;
    }

}
