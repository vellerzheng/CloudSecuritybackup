package com.mcloud.service;

import com.mcloud.service.users.UserLogin;

/**
 * Created by vellerzheng on 2017/10/13.
 */
public interface UserLoginService {

    //根据名字查找用户
    UserLogin findByName(String name) throws Exception;

    //保存用户登录信息
    void save(UserLogin userlogin) throws Exception;

    //根据姓名删除
    void removeByName(String name) throws Exception;

    //根据用户名更新
    void updateByName(String name, UserLogin userlogin);
}
