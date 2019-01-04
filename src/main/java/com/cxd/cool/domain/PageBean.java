package com.cxd.cool.domain;

import java.util.List;

import com.github.pagehelper.PageInfo;

public class PageBean<T> extends PageInfo<T>{

     //结果数据
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
