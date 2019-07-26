package com.cxd.cool.action.system;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cxd.cool.annotation.LogPrint;
import com.cxd.cool.domain.Menu;
import com.cxd.cool.service.IUserService;

@RestController
@RequestMapping(value = "/menu")
public class MenuAction {

    private Logger logger = LoggerFactory.getLogger(MenuAction.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/menu",
        method = RequestMethod.GET)
    public List<Menu> menu() {
        List<Menu> list = userService.getMenu();
        return list;
    }

    @RequestMapping(value = "/treeMenu/{userId}",
        method = RequestMethod.GET)
    public List<Menu> treeMenu(@PathVariable(name = "userId") Integer userId) {
        List<Menu> list = userService.getTreeMenuList(userId);
        return list;
    }
}
