package com.cxd.cool.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MonoTest {

    @Autowired
    @LoadBalanced
    private MongoTemplate mongoTemplate;

    public void add() {
        UserInfo u1=new UserInfo();
        u1.setId("32332");
        u1.setAddress("北京");
        u1.setAge(20);
        u1.setName("tom");

        mongoTemplate.save(u1,"");
    }
}
