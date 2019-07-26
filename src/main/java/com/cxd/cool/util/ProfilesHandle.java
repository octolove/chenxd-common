package com.cxd.cool.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cxd.cool.aspect.AspectCommon;
import com.cxd.cool.domain.Address;

@Configuration
public class ProfilesHandle {

    private Logger logger = LoggerFactory.getLogger(AspectCommon.class);

    @Bean
    @Profile("dev")
    public Address address_dev() {
        logger.info("----------------------------------->:{}", "dev");
        return new Address();
    }

    @Bean
    @Profile("test")
    public Address address_test() {
        logger.info("----------------------------------->:{}", "test");
        return new Address();
    }
}
