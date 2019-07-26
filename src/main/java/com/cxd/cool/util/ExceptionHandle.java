package com.cxd.cool.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cxd.cool.base.BusinessException;

/**
 * 统一异常处理
 */
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(BusinessException.class)
    public Map<String, String> handle(BusinessException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("code", ex.getErrorcode() + "");
        map.put("message", ex.getMessage());
        return map;
    }

    @ExceptionHandler(Exception.class)
    public Map<String, String> handleView(Exception ex) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "2");
        map.put("message", ex.getMessage());
        return map;
    }
}
