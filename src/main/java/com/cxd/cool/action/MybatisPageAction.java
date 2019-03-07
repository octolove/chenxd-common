package com.cxd.cool.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cxd.cool.domain.PageBean;
import com.cxd.cool.domain.UserInfo;
import com.cxd.cool.service.IUserService;

/**
 * jsp页面demo
 *
 */
@Controller
@RequestMapping(value = "/user")
public class MybatisPageAction {

    private Logger logger = LoggerFactory.getLogger(MybatisPageAction.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/list/{pageNum}/{pageSize}")
    public String userList(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, ModelMap map) {
        PageBean<UserInfo> pageBean = userService.list(pageNum, pageSize);
        map.put("pageInfo", pageBean);
        return "users/list";
    }

    @RequestMapping(value = "/find/{id}")
    public String userList(@PathVariable("id") Integer id, ModelMap map) {
        UserInfo uinfo = userService.findUserInfoById(id);
        map.put("uinfo", uinfo);
        return "users/userinfo";
    }
}
