package com.cxd.cool.client;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import com.alibaba.fastjson.JSONObject;
import com.cxd.cool.base.BusinessException;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

public class FeignConfig {

    private static final Logger logger = LoggerFactory.getLogger(FeignConfig.class);

    @Bean
    public ErrorDecoder feignErrorDecoder() {
        return new ErrorDecoder() {
            @Override
            public Exception decode(String methodKey, Response response) {
                String body = "";
                try {
                    if (response.body() != null) {
                        body = Util.toString(response.body().asReader());
                    }
                } catch (IOException ignored) {
                    logger.warn("response read error...");
                }

                int responseCode = response.status();
                if (responseCode >= 300 && responseCode < 600 && responseCode != 404 && responseCode != 408 && responseCode != 500) {
                    try {
                        return readForBusinessException(body);
                    } catch (Exception e) {
                        logger.warn("Business Exception can not to be create...");
                    }
                }
                return new RuntimeException("");
            }
        };
    }
    
    private BusinessException readForBusinessException(String body) {
        int code = 2;
        String message = "未知错误！";
        try {
            JSONObject jo = JSONObject.parseObject(body);
            if (jo.containsKey("errorCode") && jo.containsKey("errorMessage")) {
                code = jo.getIntValue("errorCode");
                message = jo.getString("errorMessage") == null ? "" : jo.getString("errorMessage");
            } else if (jo.containsKey("errorcode") && jo.containsKey("msg")) {
                code = jo.getIntValue("errorcode");
                message = jo.getString("msg") == null ? "" : jo.getString("msg");
            } else if (jo.containsKey("result") && jo.containsKey("message")) {
                code = jo.getIntValue("result");
                message = jo.getString("message") == null ? "" : jo.getString("message");
            }
        } catch (Exception e) {
            logger.warn("转换businessException失败！body: ", body);
        }
        return new BusinessException(code, message);
    }
}
