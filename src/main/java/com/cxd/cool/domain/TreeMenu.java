package com.cxd.cool.domain;

import java.util.List;

public class TreeMenu {
    private int id;
    private int pId;
    private String name;
    private String url;
    private int orderno;
    private int mtype;

    private List<TreeMenu> treeMenulist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<TreeMenu> getTreeMenulist() {
        return treeMenulist;
    }

    public void setTreeMenulist(List<TreeMenu> treeMenulist) {
        this.treeMenulist = treeMenulist;
    }
}
