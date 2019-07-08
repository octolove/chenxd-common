package com.cxd;

import java.io.IOException;
import java.util.UUID;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cxd.cool.Applicaton;
import com.cxd.cool.elastic.domain.EsHosts;
import com.cxd.cool.elastic.domain.UserInfo;
import com.cxd.cool.elastic.util.ElasticUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Applicaton.class })
public class EsTest {

    private Logger logger = LoggerFactory.getLogger(EsTest.class);

    @Autowired
    private EsHosts hosts;

    @Autowired
    private ElasticUtil esutil;

    // @Test
    public void test01() {
        System.out.println(hosts);
    }

    // @Test
    public void createIndex() {
        try {
            logger.info(esutil.createIndec("chenxd").toString());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    // @Test
    public void get() {
        try {
            logger.info("-----------get----------" + esutil.get("chenxd", "userinfo", "e55bf3fd-4ee4-407f-851a-7908a5139584").getSourceAsString());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    // @Test
    public void delete() {
        try {
            logger.info(esutil.delete("chenxd", "userinfo", "1559802828632 ").toString());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    // @Test
    public void createMapping() {
        try {
            esutil.createMapping("chenxd", "userinfo");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    // @Test
    public void add() {
        try {

            for (int i = 0; i < 500; i++) {
                String uid = UUID.randomUUID().toString();
                UserInfo uinfo = new UserInfo();
                uinfo.setAddress("测试");
                uinfo.setAge(i);
                uinfo.setPhone("1595800" + i);
                uinfo.setName("test_" + i);
                uinfo.setUid(uid);
                logger.info(esutil.add("chenxd", "userinfo", uinfo, uid).toString());
            }

            Thread.sleep(100);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    // @Test
    public void query() {
        try {
            // QueryBuilder query = QueryBuilders.termsQuery("name", "cxd", "tom");

            // QueryBuilder query = QueryBuilders.rangeQuery("age").gte(20).lt(30);

            QueryBuilder query = QueryBuilders.wildcardQuery("name", "test*");

            // QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.termsQuery("name", "cxd",
            // "tom")).mustNot(QueryBuilders.rangeQuery("age").gte(23).lt(30));

            esutil.query("chenxd", query);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
