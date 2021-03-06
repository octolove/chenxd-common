package com.cxd.cool.action.system;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cxd.cool.domain.LoginForm;
import com.cxd.cool.service.IUserService;
import com.cxd.cool.util.Render;

@RestController
@RequestMapping(value = "/login")
public class LoginAction {

    private Logger logger = LoggerFactory.getLogger(LoginAction.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login",
        method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody LoginForm loginForm) {
        // 验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginForm.getUsername(), loginForm.getPassword());
        subject.login(usernamePasswordToken);
        // token返回给调用方,后续请求放入请求头部
        Map<String, Object> result = Render.success();
        result.put("token", subject.getSession().getId());
        return result;
    }

    @RequestMapping(value = "/nologin",
        method = RequestMethod.GET)
    public Map<String, Object> nologin() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 301);
        map.put("message", "请先登陆");
        return map;
    }

    @RequestMapping(value = "/unauth",
        method = RequestMethod.GET)
    public Map<String, Object> unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 401);
        map.put("message", "禁止访问");
        return map;
    }

    @RequestMapping("/logout")
    public Map<String, Object> logout() {
        SecurityUtils.getSubject().logout();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 202);
        map.put("message", "成功退出");
        return map;
    }
}
