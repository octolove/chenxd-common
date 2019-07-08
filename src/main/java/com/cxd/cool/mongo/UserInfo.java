package com.cxd.cool.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userInfo")
// name：索引名称 def：字段(1正序 -1倒序) unique：是否唯一索引
// @CompoundIndexes({ @CompoundIndex(name = "uq_userno_date",
// def = "{userNo:1, date:-1}",
// unique = true) })
public class UserInfo {

    @Id
    private String id;

    private String name;

    private int age;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
