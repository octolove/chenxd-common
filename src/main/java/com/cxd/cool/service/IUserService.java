package com.cxd.cool.service;

import java.util.List;
import java.util.stream.Collectors;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxd.cool.domain.Menu;
import com.cxd.cool.domain.PageBean;
import com.cxd.cool.domain.UserInfo;
import com.cxd.cool.mapper.MenuMapper;
import com.cxd.cool.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public UserInfo findUserInfoById(Integer id) {
        return userMapper.findUserInfo(id);
    }

    /**
     * @param pageNum 页码
     * @param pageSize 每页显示数量
     */
    public PageBean<UserInfo> list(int pageNum, int pageSize) {

        if (pageNum <= 0) {
            pageNum = 1;
        }

        if (pageSize <= 0) {
            pageNum = 5;
        }

        /**
         * 用PageInfo对结果进行包装,/紧跟着的第一个select方法会被分页,
         * 后面的不会被分页，除非再次调用PageHelper.startPage
         */
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> list = userMapper.findAllUserInfo();
        PageInfo<UserInfo> page = new PageInfo<UserInfo>(list);

        // 分装结果集
        PageBean<UserInfo> pageBean = new PageBean<>();
        pageBean.setList(list);
        pageBean.setPageNum(page.getPageNum());
        pageBean.setPageSize(page.getPageSize());
        pageBean.setPages(page.getPages());
        pageBean.setNextPage(page.getNextPage());
        pageBean.setPrePage(page.getPrePage());
        pageBean.setHasNextPage(page.isHasNextPage());
        pageBean.setHasPreviousPage(page.isHasPreviousPage());
        pageBean.setIsFirstPage(page.isIsFirstPage());
        pageBean.setIsLastPage(page.isHasNextPage());
        return pageBean;
    }

    /**
     * 获取所有菜单
     */
    public List<Menu> getMenu() {
        List<Menu> menulist = menuMapper.queryMenu();
        if (menulist != null && menulist.size() > 0) {
            for (Menu m : menulist) {
                List<Menu> list = subMenu(m.getId());
                if (list != null && list.size() > 0) {
                    m.setMenulist(list);
                }
            }
        }
        return menulist;

    }

    /**
     * 根据userId(权限)获取菜单
     */
    public List<Menu> getTreeMenuList(int userId) {
        List<Menu> menuList = menuMapper.queryMenuList(userId);
        List<Menu> menulist = menuList.stream().filter(tm -> tm.getParent_id() == 0).collect(Collectors.toList());
        if (menulist != null && menulist.size() > 0) {
            for (Menu m : menulist) {
                List<Menu> list = subMenu(m.getId());
                if (list != null && list.size() > 0) {
                    m.setMenulist(list);
                }
            }
        }
        return menulist;
    }

    public List<Menu> subMenu(int parentId) {
        List<Menu> list = menuMapper.queryMenuByPid(parentId);
        if (list != null && list.size() > 0) {
            for (Menu m : list) {
                List<Menu> list2 = subMenu(m.getId());
                if (list2 != null && list2.size() > 0) {
                    m.setMenulist(list2);
                }
            }
        }
        return list;
    }

    public UserInfo getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    public List<String> getRolesByUserName(String userName) {
        return userMapper.getRolesByUserName(userName);

    }

    public List<String> getPermsByUserName(String userName) {
        return userMapper.getPermsByUserName(userName);

    }
}
