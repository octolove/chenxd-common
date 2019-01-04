package com.cxd.cool.domain;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 3015670608993048826L;

    private String uid;

    private String upass;

    private String uname;

    private String address;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
