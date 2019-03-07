package com.cxd.cool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// autoRegister=false不自动注册到eureka
@EnableDiscoveryClient(autoRegister = true)
@SpringBootApplication
@EnableFeignClients
public class Applicaton extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Applicaton.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(Applicaton.class, args);
    }
}
