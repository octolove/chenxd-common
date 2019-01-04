package com.cxd.cool.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 可换成@RestControllerAdvice
 */
@ControllerAdvice
public class BaseExceptionHandle {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Map<String, String> handle(BusinessException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("code", ex.getErrorcode() + "");
        map.put("msg", ex.getMessage());
        return map;
    }
}
