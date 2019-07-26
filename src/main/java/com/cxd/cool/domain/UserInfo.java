package com.cxd.cool.domain;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 3015670608993048826L;

    private String id;

    private String passwd;

    private String username;

    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserInfo [id=" + id + ", passwd=" + passwd + ", username=" + username + ", address=" + address + "]";
    }
}
