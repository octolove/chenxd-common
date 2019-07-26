package com.cxd.cool.action.system;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 根据用户菜单和按钮信息
     * 
     * @param userId 用户id
     * @param type 10不含按钮 -1全部菜单
     * @throws
     */
    @RequestMapping(value = "/treeMenu/{userId}/{type}",
        method = RequestMethod.GET)
    public List<Menu> treeMenu(@PathVariable(name = "userId") Integer userId, @PathVariable(name = "type") Integer type) {
        List<Menu> list = userService.getTreeMenuList(userId, type);
        return list;
    }
}
