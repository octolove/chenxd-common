package com.cxd.cool.domain;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * 分页类
 */
public class PageBean<T> extends PageInfo<T> {

    private static final long serialVersionUID = 7873561954569141935L;

    // 结果数据
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
