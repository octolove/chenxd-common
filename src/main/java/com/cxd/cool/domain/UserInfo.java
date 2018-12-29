package com.cxd.cool.domain;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 3015670608993048826L;

    private String uid;

    private String sex;

    private String uname;

    private Address address;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
