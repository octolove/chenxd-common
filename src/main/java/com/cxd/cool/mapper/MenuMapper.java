package com.cxd.cool.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxd.cool.domain.Menu;

public interface MenuMapper {

    public List<Menu> queryMenu();

    public List<Menu> queryMenuByPid(@Param(value = "parentId") int parentId, @Param(value = "mtype") int mtype);

    public List<Menu> queryMenuList(@Param(value = "userId") int userId, @Param(value = "mtype") int mtype);

}
