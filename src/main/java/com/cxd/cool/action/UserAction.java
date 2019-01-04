package com.cxd.cool.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * jsp页面
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserAction {

    private Logger logger = LoggerFactory.getLogger(UserAction.class);

    @RequestMapping(value = "/testjsp")
    public String testjsp() {
        logger.info(">>>>>>>>>>>>>>>>>>>>testjsp");
        return "test";

    }

    @RequestMapping(value = "/list")
    public String userList(ModelMap map) {
        return "users/userinfo";
    }
}
