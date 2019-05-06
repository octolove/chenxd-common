package com.cxd.cool.util;

import java.util.HashMap;
import java.util.Map;

public class Kit {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");

        //key存在则走后面的函数拼接，key不存在则新增一个key
        map.merge("awww", "T", (v1, v2) -> v1 + v2);
        System.out.println(map);

        // map.forEach((Key,value)->System.out.println(value));
    }

}
