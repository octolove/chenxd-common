package com.cxd.cool.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxd.cool.base.BusinessException;
import com.cxd.cool.domain.UserInfo;

/**
 * redis和mq的demo
 * 默认返回json
 *
 */
@RestController
@RequestMapping(value = "/test")
public class HelloAction {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/sayhello3")
    public String sayHello3() {

        redisTemplate.opsForValue().set("qq", "525104085");
        redisTemplate.opsForHash().put("cxd", "name", "xd");
        redisTemplate.opsForHash().put("cxd", "age", "11");
        redisTemplate.opsForHash().put("cxd", "address", "nj");

        rabbitTemplate.convertAndSend("cxdTopicExchange", "cxd.1", new Date().getTime());
        rabbitTemplate.convertAndSend("cxdTopicExchange2", "cxd2.1", new Date().getTime());
        return "hello word";

    }

    @RequestMapping(value = "/sayhello2")
    public List<String> sayHello2(@RequestBody UserInfo userInfo) {

        List<String> list = new ArrayList<String>();
        list.add("111");
        list.add("222");
        list.add("333");

        return list;

    }

    @RequestMapping(value = "/sayhello")
    public String sayHello() {

        throw new BusinessException(1000, "请求参数不合法");

    }
}
