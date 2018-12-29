package com.cxd.cool.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
//@RestControllerAdvice
public class BaseExceptionHandle {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, String> handle(Exception ex) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "-1");
        map.put("msg", ex.getMessage());
        return map;
    }
}
