package com.cxd.cool.mapper;

import java.util.List;

import com.cxd.cool.domain.UserInfo;

public interface UserMapper {

    public UserInfo findUserInfo(Integer id);

    public UserInfo inserUser(UserInfo userInfo);

    public List<UserInfo> findAllUserInfo();

}
