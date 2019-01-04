package com.cxd.cool.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxd.cool.base.BusinessException;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Map<String, String> handle(BusinessException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("code", ex.getErrorcode() + "");
        map.put("msg", ex.getMessage());
        return map;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleView(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", ex.getMessage());
        mav.setViewName("error");
        return mav;
    }
}
