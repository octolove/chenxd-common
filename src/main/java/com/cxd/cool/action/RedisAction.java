package com.cxd.cool.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxd.cool.annotation.LogPrint;

@RestController
@RequestMapping(value = "/redis")
public class RedisAction {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @LogPrint
    @RequestMapping(value = "/queue")
    public String redisqueue() {

        stringRedisTemplate.convertAndSend("mytopic", "queue is ok");
        return "redisqueue";
    }
}
