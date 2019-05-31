package com.cxd.cool.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "userinfo")
public class UserinfoEntity {

    @Id
    private Integer id;

    private String username;

    private String passwd;

    private String address;

    @OneToMany(mappedBy = "userinfoEntity")
    private List<AddressEntity> addressEntitys;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<AddressEntity> getAddressEntitys() {
        return addressEntitys;
    }

    public void setAddressEntitys(List<AddressEntity> addressEntitys) {
        this.addressEntitys = addressEntitys;
    }

    @Override
    public String toString() {
        return "UserinfoEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", address='" + address + '\'' +
                ", addressEntitys=" + addressEntitys +
                '}';
    }
}
