package com.cxd.cool.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class Kit {

    public static String getSuffixByUserId(String userId) {

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] result = digest.digest(userId.getBytes("UTF-8"));
            return String.valueOf((result[15] + 128) % 64);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");

        //key存在则走后面的函数拼接，key不存在则新增一个key
        map.merge("awww", "T", (v1, v2) -> v1 + v2);
        System.out.println(map);

        map.forEach((Key, value) -> System.out.println(value));

        System.out.print("------" + getSuffixByUserId("cd18a091-593e-4e29-89f9-1ccdf89fa2f7_9"));

        File file = new File("E:/1122.txt");

        try (InputStream in = new FileInputStream(file)) {

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
