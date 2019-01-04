package com.cxd.cool.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest")
public class LoginAction {
    
    @RequestMapping(value = "/login")
    public String sayHello2() {

        return "login";

    }

}
