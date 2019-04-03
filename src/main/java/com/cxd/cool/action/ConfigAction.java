package com.cxd.cool.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/config")
public class ConfigAction {

   // @Value("${import.manualDataPath}")
    private String manualDataPaths;

    @RequestMapping(value = "/test")
    public String testok() {
        return manualDataPaths;
    }

}
