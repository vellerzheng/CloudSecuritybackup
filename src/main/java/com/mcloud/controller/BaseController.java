package com.mcloud.controller;

import com.mcloud.model.UserAdviceEntity;
import com.mcloud.model.UsersEntity;
import com.mcloud.repository.UserAdviceRepository;
import com.mcloud.service.supportToolClass.converter.CustomDateConverter;
import com.mcloud.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vellerzheng on 2017/9/20.
 */
@Controller
public class BaseController {

    @Autowired
    UserAdviceRepository userAdviceRepository;

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public  String index() {

        return "index.html";
    }

    @RequestMapping(value ="/clouds/error", method = RequestMethod.GET)
    public String getError(){
        return "clouds/error";
    }

    @RequestMapping(value ="/clouds/home", method = RequestMethod.GET)
    public String getHome(){
        return "clouds/home";
    }


    @RequestMapping(value = "/clouds/users/admin/welcomeAdmin/{userName}", method = RequestMethod.GET)
    public String getWelcomeAdmin(ModelMap modelMap, @PathVariable("userName")String userName) {
        UsersEntity utyAdmin = (UsersEntity) redisUtil.get(userName);
        modelMap.addAttribute("loginUser",utyAdmin);
        return "clouds/users/admin/welcomeAdmin";
    }

    @RequestMapping(value = "/clouds/users/default/welcome/{userName}", method = RequestMethod.GET)
    public String getWelcome(ModelMap modelMap, @PathVariable("userName")String userName) {
        UsersEntity ordinaryUser = (UsersEntity) redisUtil.get(userName);
        modelMap.addAttribute("loginUser",ordinaryUser);
        return "clouds/users/default/welcome";
    }

    @RequestMapping(value = "/clouds/users/top")
    public String getTop() { return "clouds/utils/account"; }

    @RequestMapping(value = "/clouds/users/logout", method = RequestMethod.GET)
    public String logOut() { return "clouds/users/logout"; }

    @RequestMapping(value = "/clouds/users/passwordReset", method = RequestMethod.GET)
    public String getPasswordReset() {
        return "clouds/users/passwordReset";
    }

    @RequestMapping(value = "/js/AJAX.js/adviceUpload",method = RequestMethod.POST)
    @ResponseBody
    public String getPublicAdvice(HttpServletRequest request, HttpServletResponse response, @RequestBody UserAdviceEntity userAdviceEntity) throws IOException {


        String email = userAdviceEntity.getEmail();
        String name = userAdviceEntity.getName();
        String idea =userAdviceEntity.getMainIdea();
        String message = userAdviceEntity.getMessageDetail();
        userAdviceEntity.setSubmitTime(CustomDateConverter.currentTime());
        userAdviceRepository.saveAndFlush(userAdviceEntity);

        return "upload advice successfully!";
    }

}

