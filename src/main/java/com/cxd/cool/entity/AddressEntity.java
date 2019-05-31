package com.cxd.cool.entity;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    private Integer id;

    private String addname;

    private String phone;

    private String city;

    @ManyToOne
    @JoinColumn(name = "uid")
    private UserinfoEntity userinfoEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddname() {
        return addname;
    }

    public void setAddname(String addname) {
        this.addname = addname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UserinfoEntity getUserinfoEntity() {
        return userinfoEntity;
    }

    public void setUserinfoEntity(UserinfoEntity userinfoEntity) {
        this.userinfoEntity = userinfoEntity;
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "id=" + id +
                ", addname='" + addname + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
