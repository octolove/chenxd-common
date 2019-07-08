package com.cxd.cool.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

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

        map.forEach((Key, value) -> System.out.println(value));

        //-------------------------------------------
        List<Integer> lists = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 87, 876, 87, 7, 9, 7);
        Optional<Integer> op = lists.stream().max((k1, k2) -> k1 - k2);

        Optional<String> optional = Optional.of("cxd");


        optional.ifPresent(p-> System.out.print(p));
        Supplier<String> supplier = () -> "555";



        String oldstr="{\n" +
                "\t\"pmId\": \"37533\",\n" +
                "\t\"baseId\": \"000\",\n" +
                "\t\"sizeId\": \"000\",\n" +
                "\t\"base\": {\n" +
                "\t\t\"baseId\": \"000\",\n" +
                "\t\t\"description\": \"          \",\n" +
                "\t\t\"abbr\": null,\n" +
                "\t\t\"descEn\": null,\n" +
                "\t\t\"descCn\": \"111.0\",\n" +
                "\t\t\"sort\": 0\n" +
                "\t},\n" +
                "\t\"size\": {\n" +
                "\t\t\"sizeId\": \"000\",\n" +
                "\t\t\"description\": \"          \",\n" +
                "\t\t\"abbr\": null,\n" +
                "\t\t\"descEn\": null,\n" +
                "\t\t\"descCn\": \" \",\n" +
                "\t\t\"sort\": 0\n" +
                "\t},\n" +
                "\t\"menuFlag\": \"M\",\n" +
                "\t\"mealItems\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"pmId\": \"6219\",\n" +
                "\t\t\t\"baseId\": \"000\",\n" +
                "\t\t\t\"sizeId\": \"000\",\n" +
                "\t\t\t\"base\": {\n" +
                "\t\t\t\t\"baseId\": \"000\",\n" +
                "\t\t\t\t\"description\": \"          \",\n" +
                "\t\t\t\t\"abbr\": null,\n" +
                "\t\t\t\t\"descEn\": null,\n" +
                "\t\t\t\t\"descCn\": \"111.0\",\n" +
                "\t\t\t\t\"sort\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"size\": {\n" +
                "\t\t\t\t\"sizeId\": \"000\",\n" +
                "\t\t\t\t\"description\": \"          \",\n" +
                "\t\t\t\t\"abbr\": null,\n" +
                "\t\t\t\t\"descEn\": null,\n" +
                "\t\t\t\t\"descCn\": \" \",\n" +
                "\t\t\t\t\"sort\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"menuFlag\": \"P\",\n" +
                "\t\t\t\"price\": 0,\n" +
                "\t\t\t\"nameCn\": \"C一块吮指原味鸡\",\n" +
                "\t\t\t\"nameEn\": \"\",\n" +
                "\t\t\t\"showNameEn\": \"\",\n" +
                "\t\t\t\"showNameCn\": \"C一块吮指原味鸡\",\n" +
                "\t\t\t\"maxQty\": \"5\",\n" +
                "\t\t\t\"abbr\": \"CYKSZYWJ\",\n" +
                "\t\t\t\"imageUrl\": \"604_585916.jpg\",\n" +
                "\t\t\t\"saleFlag\": 1,\n" +
                "\t\t\t\"nounId\": 4145,\n" +
                "\t\t\t\"nounName\": \"C一块吮指原味鸡\",\n" +
                "\t\t\t\"linkedId\": 26627,\n" +
                "\t\t\t\"itemDept\": 10,\n" +
                "\t\t\t\"itemClass\": 1,\n" +
                "\t\t\t\"financialDept\": 1,\n" +
                "\t\t\t\"financialClass\": 1,\n" +
                "\t\t\t\"mealdealId\": 2,\n" +
                "\t\t\t\"weekly\": \"1111111\",\n" +
                "\t\t\t\"toppingMaxCount\": 0,\n" +
                "\t\t\t\"toppingWeight\": 0,\n" +
                "\t\t\t\"price1\": 0,\n" +
                "\t\t\t\"price2\": 0,\n" +
                "\t\t\t\"price3\": 0,\n" +
                "\t\t\t\"defaultSelected\": \"1\",\n" +
                "\t\t\t\"altDesc\": \"\",\n" +
                "\t\t\t\"eGiftCardPaymentSupport\": 1,\n" +
                "\t\t\t\"adjustPrice\": 0,\n" +
                "\t\t\t\"memberProduct\": 0,\n" +
                "\t\t\t\"itype\": \"\",\n" +
                "\t\t\t\"holiday\": false\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"pmId\": \"6220\",\n" +
                "\t\t\t\"baseId\": \"000\",\n" +
                "\t\t\t\"sizeId\": \"000\",\n" +
                "\t\t\t\"base\": {\n" +
                "\t\t\t\t\"baseId\": \"000\",\n" +
                "\t\t\t\t\"description\": \"          \",\n" +
                "\t\t\t\t\"abbr\": null,\n" +
                "\t\t\t\t\"descEn\": null,\n" +
                "\t\t\t\t\"descCn\": \"111.0\",\n" +
                "\t\t\t\t\"sort\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"size\": {\n" +
                "\t\t\t\t\"sizeId\": \"000\",\n" +
                "\t\t\t\t\"description\": \"          \",\n" +
                "\t\t\t\t\"abbr\": null,\n" +
                "\t\t\t\t\"descEn\": null,\n" +
                "\t\t\t\t\"descCn\": \" \",\n" +
                "\t\t\t\t\"sort\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"menuFlag\": \"P\",\n" +
                "\t\t\t\"price\": 0,\n" +
                "\t\t\t\"nameCn\": \"C二块香辣鸡翅\",\n" +
                "\t\t\t\"nameEn\": \"\",\n" +
                "\t\t\t\"showNameEn\": \"\",\n" +
                "\t\t\t\"showNameCn\": \"C二块香辣鸡翅\",\n" +
                "\t\t\t\"maxQty\": \"3\",\n" +
                "\t\t\t\"abbr\": \"CEKXLJC\",\n" +
                "\t\t\t\"imageUrl\": \"604_585918.jpg\",\n" +
                "\t\t\t\"saleFlag\": 1,\n" +
                "\t\t\t\"nounId\": 4192,\n" +
                "\t\t\t\"nounName\": \"C二块香辣鸡翅\",\n" +
                "\t\t\t\"linkedId\": 26648,\n" +
                "\t\t\t\"itemDept\": 10,\n" +
                "\t\t\t\"itemClass\": 1,\n" +
                "\t\t\t\"financialDept\": 1,\n" +
                "\t\t\t\"financialClass\": 1,\n" +
                "\t\t\t\"mealdealId\": 3,\n" +
                "\t\t\t\"weekly\": \"1111111\",\n" +
                "\t\t\t\"toppingMaxCount\": 0,\n" +
                "\t\t\t\"toppingWeight\": 0,\n" +
                "\t\t\t\"price1\": 0,\n" +
                "\t\t\t\"price2\": 0,\n" +
                "\t\t\t\"price3\": 0,\n" +
                "\t\t\t\"defaultSelected\": \"1\",\n" +
                "\t\t\t\"altDesc\": \"\",\n" +
                "\t\t\t\"eGiftCardPaymentSupport\": 1,\n" +
                "\t\t\t\"adjustPrice\": 0,\n" +
                "\t\t\t\"memberProduct\": 0,\n" +
                "\t\t\t\"itype\": \"\",\n" +
                "\t\t\t\"holiday\": false\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"pmId\": \"6235\",\n" +
                "\t\t\t\"baseId\": \"000\",\n" +
                "\t\t\t\"sizeId\": \"000\",\n" +
                "\t\t\t\"base\": {\n" +
                "\t\t\t\t\"baseId\": \"000\",\n" +
                "\t\t\t\t\"description\": \"          \",\n" +
                "\t\t\t\t\"abbr\": null,\n" +
                "\t\t\t\t\"descEn\": null,\n" +
                "\t\t\t\t\"descCn\": \"111.0\",\n" +
                "\t\t\t\t\"sort\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"size\": {\n" +
                "\t\t\t\t\"sizeId\": \"000\",\n" +
                "\t\t\t\t\"description\": \"          \",\n" +
                "\t\t\t\t\"abbr\": null,\n" +
                "\t\t\t\t\"descEn\": null,\n" +
                "\t\t\t\t\"descCn\": \" \",\n" +
                "\t\t\t\t\"sort\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"menuFlag\": \"P\",\n" +
                "\t\t\t\"price\": 0,\n" +
                "\t\t\t\"nameCn\": \"C醇香土豆泥\",\n" +
                "\t\t\t\"nameEn\": \"\",\n" +
                "\t\t\t\"showNameEn\": \"\",\n" +
                "\t\t\t\"showNameCn\": \"C醇香土豆泥\",\n" +
                "\t\t\t\"maxQty\": \"1\",\n" +
                "\t\t\t\"abbr\": \"CCXTDN\",\n" +
                "\t\t\t\"imageUrl\": \"604_580904.jpg\",\n" +
                "\t\t\t\"saleFlag\": 1,\n" +
                "\t\t\t\"nounId\": 4200,\n" +
                "\t\t\t\"nounName\": \"C醇香土豆泥\",\n" +
                "\t\t\t\"linkedId\": 26660,\n" +
                "\t\t\t\"itemDept\": 10,\n" +
                "\t\t\t\"itemClass\": 1,\n" +
                "\t\t\t\"financialDept\": 1,\n" +
                "\t\t\t\"financialClass\": 1,\n" +
                "\t\t\t\"mealdealId\": 4,\n" +
                "\t\t\t\"weekly\": \"1111111\",\n" +
                "\t\t\t\"toppingMaxCount\": 0,\n" +
                "\t\t\t\"toppingWeight\": 0,\n" +
                "\t\t\t\"price1\": 0,\n" +
                "\t\t\t\"price2\": 0,\n" +
                "\t\t\t\"price3\": 0,\n" +
                "\t\t\t\"defaultSelected\": \"1\",\n" +
                "\t\t\t\"altDesc\": \"\",\n" +
                "\t\t\t\"eGiftCardPaymentSupport\": 1,\n" +
                "\t\t\t\"adjustPrice\": 0,\n" +
                "\t\t\t\"memberProduct\": 0,\n" +
                "\t\t\t\"itype\": \"\",\n" +
                "\t\t\t\"holiday\": false\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"pmId\": \"6225\",\n" +
                "\t\t\t\"baseId\": \"000\",\n" +
                "\t\t\t\"sizeId\": \"000\",\n" +
                "\t\t\t\"base\": {\n" +
                "\t\t\t\t\"baseId\": \"000\",\n" +
                "\t\t\t\t\"description\": \"          \",\n" +
                "\t\t\t\t\"abbr\": null,\n" +
                "\t\t\t\t\"descEn\": null,\n" +
                "\t\t\t\t\"descCn\": \"111.0\",\n" +
                "\t\t\t\t\"sort\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"size\": {\n" +
                "\t\t\t\t\"sizeId\": \"000\",\n" +
                "\t\t\t\t\"description\": \"          \",\n" +
                "\t\t\t\t\"abbr\": null,\n" +
                "\t\t\t\t\"descEn\": null,\n" +
                "\t\t\t\t\"descCn\": \" \",\n" +
                "\t\t\t\t\"sort\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"menuFlag\": \"P\",\n" +
                "\t\t\t\"price\": 0,\n" +
                "\t\t\t\"nameCn\": \"C香甜粟米棒\",\n" +
                "\t\t\t\"nameEn\": \"\",\n" +
                "\t\t\t\"showNameEn\": \"\",\n" +
                "\t\t\t\"showNameCn\": \"C香甜粟米棒\",\n" +
                "\t\t\t\"maxQty\": \"1\",\n" +
                "\t\t\t\"abbr\": \"CXTSMB\",\n" +
                "\t\t\t\"imageUrl\": \"604_585921.jpg\",\n" +
                "\t\t\t\"saleFlag\": 1,\n" +
                "\t\t\t\"nounId\": 4197,\n" +
                "\t\t\t\"nounName\": \"C香甜粟米棒\",\n" +
                "\t\t\t\"linkedId\": 26659,\n" +
                "\t\t\t\"itemDept\": 10,\n" +
                "\t\t\t\"itemClass\": 1,\n" +
                "\t\t\t\"financialDept\": 1,\n" +
                "\t\t\t\"financialClass\": 1,\n" +
                "\t\t\t\"mealdealId\": 5,\n" +
                "\t\t\t\"weekly\": \"1111111\",\n" +
                "\t\t\t\"toppingMaxCount\": 0,\n" +
                "\t\t\t\"toppingWeight\": 0,\n" +
                "\t\t\t\"price1\": 0,\n" +
                "\t\t\t\"price2\": 0,\n" +
                "\t\t\t\"price3\": 0,\n" +
                "\t\t\t\"defaultSelected\": \"1\",\n" +
                "\t\t\t\"altDesc\": \"\",\n" +
                "\t\t\t\"eGiftCardPaymentSupport\": 1,\n" +
                "\t\t\t\"adjustPrice\": 0,\n" +
                "\t\t\t\"memberProduct\": 0,\n" +
                "\t\t\t\"itype\": \"\",\n" +
                "\t\t\t\"holiday\": false\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"pmId\": \"6361\",\n" +
                "\t\t\t\"baseId\": \"000\",\n" +
                "\t\t\t\"sizeId\": \"000\",\n" +
                "\t\t\t\"base\": {\n" +
                "\t\t\t\t\"baseId\": \"000\",\n" +
                "\t\t\t\t\"description\": \"          \",\n" +
                "\t\t\t\t\"abbr\": null,\n" +
                "\t\t\t\t\"descEn\": null,\n" +
                "\t\t\t\t\"descCn\": \"111.0\",\n" +
                "\t\t\t\t\"sort\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"size\": {\n" +
                "\t\t\t\t\"sizeId\": \"000\",\n" +
                "\t\t\t\t\"description\": \"          \",\n" +
                "\t\t\t\t\"abbr\": null,\n" +
                "\t\t\t\t\"descEn\": null,\n" +
                "\t\t\t\t\"descCn\": \" \",\n" +
                "\t\t\t\t\"sort\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"menuFlag\": \"P\",\n" +
                "\t\t\t\"price\": 0,\n" +
                "\t\t\t\"nameCn\": \"C1.25L瓶装可乐\",\n" +
                "\t\t\t\"nameEn\": \"\",\n" +
                "\t\t\t\"showNameEn\": \"\",\n" +
                "\t\t\t\"showNameCn\": \"C1.25L瓶装可乐\",\n" +
                "\t\t\t\"maxQty\": \"1\",\n" +
                "\t\t\t\"abbr\": \"C0000LPZKL\",\n" +
                "\t\t\t\"imageUrl\": \"604_585920.jpg\",\n" +
                "\t\t\t\"saleFlag\": 1,\n" +
                "\t\t\t\"nounId\": 4208,\n" +
                "\t\t\t\"nounName\": \"C1.25L瓶装可乐\",\n" +
                "\t\t\t\"linkedId\": 26671,\n" +
                "\t\t\t\"itemDept\": 10,\n" +
                "\t\t\t\"itemClass\": 2,\n" +
                "\t\t\t\"financialDept\": 1,\n" +
                "\t\t\t\"financialClass\": 1,\n" +
                "\t\t\t\"mealdealId\": 6,\n" +
                "\t\t\t\"weekly\": \"1111111\",\n" +
                "\t\t\t\"toppingMaxCount\": 0,\n" +
                "\t\t\t\"toppingWeight\": 0,\n" +
                "\t\t\t\"price1\": 0,\n" +
                "\t\t\t\"price2\": 0,\n" +
                "\t\t\t\"price3\": 0,\n" +
                "\t\t\t\"defaultSelected\": \"1\",\n" +
                "\t\t\t\"altDesc\": \"\",\n" +
                "\t\t\t\"eGiftCardPaymentSupport\": 1,\n" +
                "\t\t\t\"adjustPrice\": 0,\n" +
                "\t\t\t\"memberProduct\": 0,\n" +
                "\t\t\t\"itype\": \"\",\n" +
                "\t\t\t\"holiday\": false\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"price\": 9600,\n" +
                "\t\"nameCn\": \"D外送全家桶\",\n" +
                "\t\"nameEn\": \"Bucket\",\n" +
                "\t\"showNameEn\": \"Bucket\",\n" +
                "\t\"showNameCn\": \"外送全家桶\",\n" +
                "\t\"maxQty\": \"99\",\n" +
                "\t\"abbr\": \"DWSQJT\",\n" +
                "\t\"imageUrl\": \"604_585865.jpg\",\n" +
                "\t\"saleFlag\": 0,\n" +
                "\t\"dictionaryISalesFlag\": \"Y\",\n" +
                "\t\"nounId\": 3119,\n" +
                "\t\"nounName\": \"D外带全家桶\",\n" +
                "\t\"linkedId\": 38930,\n" +
                "\t\"itemDept\": 0,\n" +
                "\t\"itemClass\": 0,\n" +
                "\t\"financialDept\": 0,\n" +
                "\t\"financialClass\": 0,\n" +
                "\t\"mealdealId\": 650203,\n" +
                "\t\"allowCond\": 0,\n" +
                "\t\"modRequired\": 0,\n" +
                "\t\"itemCode\": \"          \",\n" +
                "\t\"specialColor\": \"\",\n" +
                "\t\"weekly\": \"1111111\",\n" +
                "\t\"toppingMaxCount\": 0,\n" +
                "\t\"toppingWeight\": 0,\n" +
                "\t\"price1\": 0,\n" +
                "\t\"price2\": 0,\n" +
                "\t\"price3\": 0,\n" +
                "\t\"altDesc\": \"\",\n" +
                "\t\"eGiftCardPaymentSupport\": 1,\n" +
                "\t\"adjustPrice\": 0,\n" +
                "\t\"memberProduct\": 0,\n" +
                "\t\"itype\": \"\",\n" +
                "\t\"holiday\": true\n" +
                "}";
        JSONObject jsonObject=JSON.parseObject(oldstr);
        System.out.println(JSON.toJSONString(jsonObject, SerializerFeature.MapSortField));
    }

}
