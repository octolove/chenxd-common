package com.cxd.cool.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxd.cool.domain.UserInfo;

public interface UserMapper {

    public UserInfo findUserInfo(Integer id);

    public UserInfo inserUser(UserInfo userInfo);

    public List<UserInfo> findAllUserInfo();

    public UserInfo getUserByUserName(@Param(value = "userName") String userName);
    
    public List<String> getRolesByUserName(@Param(value = "userName") String userName);
    
    public List<String> getPermsByUserName(@Param(value = "userName") String userName);

}
