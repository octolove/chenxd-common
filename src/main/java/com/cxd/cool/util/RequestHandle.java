package com.cxd.cool.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 拦截器
 */
public class RequestHandle extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(RequestHandle.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info(">>>>>>>>>>>>>>>>>>>>>>>RequestHandle");

        return true;
    }

}
