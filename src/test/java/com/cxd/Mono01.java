package com.cxd;

import com.cxd.cool.Applicaton;
import com.cxd.cool.mongo.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Applicaton.class})
public class Mono01 {

    @Autowired
    private MongoTemplate mongoTemplate;

    // @Test
    public void add() {
        UserInfo u1 = new UserInfo();
        u1.setId("32332");
        u1.setAddress("北京");
        u1.setAge(20);
        u1.setName("tom");

        mongoTemplate.save(u1, "userInfo");
    }

    //@Test
    public void serach() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("jack"));
        query.addCriteria(Criteria.where("age").is(59));

        //从0开始查询10条
        //query.skip(0).limit(100);

        List<UserInfo> lists = mongoTemplate.find(query, UserInfo.class);
        //名字叫"水车"的,并且年龄是20岁
        //Criteria.where("name").is("水车").and("age").is(20)

        //名字叫"水车"的或者是绰号叫"轩哥"
        //(new Criteria().orOperator(Criteria.where("name").is("水车"), Criteria.where("nickname").is("轩哥"))

        // 查询 name中包含"轩" 这一个字的 多条信息 --模糊查询
        //query.addCriteria(Criteria.where("name").regex("轩");
        System.out.println(lists);
    }
}
