package com.cxd.cool.elastic.util;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

public class MappingUtil {

    public static XContentBuilder createCustomerMapping() {
        XContentBuilder mapping = null;
        try {
            mapping = XContentFactory.jsonBuilder().startObject().startObject("properties")

            .startObject("uid").field("type", "keyword").field("index", "false").endObject() // 默认true

                .startObject("name").field("type", "keyword").field("index", "true").endObject()

                .startObject("age").field("type", "byte").field("index", "true").endObject()

                .startObject("phone").field("type", "keyword").field("index", "true").endObject()

                // ik分词 ik_max_word,ik_smart
                .startObject("address").field("type", "text").field("index", "true").field("analyzer", "ik_max_word").field("search_analyzer", "ik_max_word").endObject()

                .startObject("birtydate").field("type", "date").field("index", "true").endObject()

                // .startObject("birtydate").field("type", "date").field("index",
                // "true").field("format","yyyy-MM-dd HH:mm:ss").endObject()

                .endObject().endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mapping;
    }
}
