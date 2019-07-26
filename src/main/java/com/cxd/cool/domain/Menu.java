package com.cxd.cool.domain;

import java.util.List;

public class Menu {
    private int id;
    private int parent_id;
    private String mname;
    private String url;
    private int orderno;
    private int mtype;

    private List<Menu> menulist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOrderno() {
        return orderno;
    }

    public void setOrderno(int orderno) {
        this.orderno = orderno;
    }

    public int getMtype() {
        return mtype;
    }

    public void setMtype(int mtype) {
        this.mtype = mtype;
    }

    public List<Menu> getMenulist() {
        return menulist;
    }

    public void setMenulist(List<Menu> menulist) {
        this.menulist = menulist;
    }
}
