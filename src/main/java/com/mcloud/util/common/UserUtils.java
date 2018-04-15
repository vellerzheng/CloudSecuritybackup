package com.mcloud.util.common;

import com.mcloud.model.UsersEntity;
import com.mcloud.repository.UserRepository;
import com.mcloud.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserRepository userRepository;

    public UsersEntity getUsersEntity(String userName){
        UsersEntity loginUser =null;
        loginUser = (UsersEntity) redisUtil.get(userName);
        if(loginUser == null ){
            loginUser =userRepository.findByUsernameEndsWith(userName);
            if(loginUser != null)
                redisUtil.setEx(loginUser.getUsername(),10000,loginUser);
        }
        return loginUser;
    }


}
