package com.mcloud.service.impl;

import com.mcloud.model.UsersEntity;
import com.mcloud.repository.UserRepository;
import com.mcloud.service.UserLoginService;
import com.mcloud.model.common.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vellerzheng on 2017/10/13.
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserLogin findByName(String name) throws Exception {
        UsersEntity usersEntity = userRepository.findByUsernameEndsWith(name);
        UserLogin userLogin=null;
        userLogin.setUsername(usersEntity.getUsername());
        userLogin.setPassword(usersEntity.getPassword());
        return  userLogin;
    }

    @Override
    public void save(UserLogin userlogin) throws Exception {

    }

    @Override
    public void removeByName(String name) throws Exception {

    }

    @Override
    public void updateByName(String name, UserLogin userlogin) {

    }
}
