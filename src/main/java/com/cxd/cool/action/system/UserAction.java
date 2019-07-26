package com.cxd.cool.action.system;

import com.cxd.cool.entity.UserinfoEntity;
import com.cxd.cool.repository.UserRepository;
import com.cxd.cool.service.IUserService;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserAction {

    private Logger logger = LoggerFactory.getLogger(UserAction.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/findAll",
        method = RequestMethod.GET)
    public String findAll() {
        List<UserinfoEntity> uinfos = userRepository.findAll();
        logger.info(uinfos.toString());
        return "OK";
    }

    @RequestMapping(value = "/findBy",
        method = RequestMethod.GET)
    public String findBy(@RequestParam(name = "username") String username, @RequestParam(name = "passwd") String passwd) {
        List<UserinfoEntity> uinfos = userRepository.findByUsernameAndPasswd(username, passwd);
        logger.info(uinfos.toString());
        return "OK";
    }

    // 需要sytem:user:add权限
    @RequiresPermissions({ "sytem:user:add" })
    @RequestMapping(value = "/findById/{id}",
        method = RequestMethod.GET)
    public String findById(@PathVariable(name = "id") Integer id) {
        Optional<UserinfoEntity> optional = userRepository.findById(id);
        UserinfoEntity u = optional.orElse(new UserinfoEntity());
        logger.info(u.toString());
        return u.toString();
    }

    // 需要admin角色
    @RequiresRoles(value = { "admin" })
    @RequestMapping(value = "/add",
        method = RequestMethod.POST)
    public String add(@RequestBody UserinfoEntity userinfoEntity) {
        // userRepository.save(userinfoEntity);
        return "add-OK";
    }

    //需要登陆
    @RequiresAuthentication
    @RequestMapping(value = "/roles/{username}",
        method = RequestMethod.GET)
    public List<String> getRoles(@PathVariable(name = "username") String userName) {
        return userService.getRolesByUserName(userName);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/perms/{username}",
        method = RequestMethod.GET)
    public List<String> getPerms(@PathVariable(name = "username") String userName) {
        return userService.getPermsByUserName(userName);
    }
}
