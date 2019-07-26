package com.cxd.cool.shiro;

import java.util.HashSet;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cxd.cool.domain.UserInfo;
import com.cxd.cool.service.IUserService;

public class UserLoginRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        UserInfo uinfo = (UserInfo) principals.getPrimaryPrincipal();
        // 根据username查询权限和角色,直接通过userId查询更简单
        List<String> roles = userService.getRolesByUserName(uinfo.getUsername());
        List<String> perms = userService.getPermsByUserName(uinfo.getUsername());
        // 权限
        info.setStringPermissions(new HashSet<String>(perms));
        // 角色
        info.setRoles(new HashSet<String>(roles));
        return info;
    }

    /**
     * 用户认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }
        String userName = token.getPrincipal().toString();
        UserInfo uinfo = userService.getUserByUserName(userName);
        if (uinfo == null) {
            throw new UnknownAccountException();
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(uinfo, uinfo.getPasswd(), getName());
        return authenticationInfo;
    }

}
