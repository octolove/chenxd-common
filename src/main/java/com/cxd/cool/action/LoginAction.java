package com.cxd.cool.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest")
public class LoginAction {

    private Logger logger = LoggerFactory.getLogger(LoginAction.class);

    @RequestMapping(value = "/test1/{name}")
    public String test(@PathVariable("name") String name) {
        logger.info("--------------1--------------->" + name);
        return "elas";
    }

    @RequestMapping(value = "/test2")
    public String test2(@RequestParam(name="name",required=true) String name) {
        logger.info("-------------2---------------->" + name);
        return "elas";
    }

    @RequestMapping(value = "/test3")
    public String test3(@PathVariable(value="name") String name) {
        logger.info("--------------3--------------->" + name);
        return "elas";
    }

    @RequestMapping(value = "/test4")
    public String test4(@RequestParam(value="name",required=true) String name) {
        logger.info("------------4----------------->" + name);
        return "elas";
    }

}
