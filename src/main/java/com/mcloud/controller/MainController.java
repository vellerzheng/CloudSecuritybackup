package com.mcloud.controller;

import com.mcloud.model.UserAdviceEntity;
import com.mcloud.repository.UserAdviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vellerzheng on 2017/9/20.
 */
@Controller
public class MainController {

    @Autowired
    UserAdviceRepository userAdviceRepository;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public  String index() {return "index.html";}



    @RequestMapping(value ="/clouds/error", method = RequestMethod.GET)
    public String getError(){
        return "clouds/error";
    }

    @RequestMapping(value ="/clouds/home", method = RequestMethod.GET)
    public String getHome(){
        return "clouds/home";
    }


    @RequestMapping(value = "/clouds/welcome", method = RequestMethod.GET)
    public String getWelcome() {
        return "clouds/welcome";
    }

    @RequestMapping(value = "/clouds/users/top")
    public String getTop() { return "clouds/users/top"; }

    @RequestMapping(value = "/clouds/users/logout", method = RequestMethod.GET)
    public String logOut() { return "clouds/users/logout"; }

    @RequestMapping(value = "/clouds/users/passwordReset", method = RequestMethod.GET)
    public String getPasswordReset() {
        return "clouds/users/passwordReset";
    }

    @RequestMapping(value = "/public/adviceUpload",method = RequestMethod.POST)
    public String getPublicAdvice(HttpServletRequest request, @ModelAttribute("advice")UserAdviceEntity userAdviceEntity) {
       /* userAdviceEntity.setSubmitTime(CustomDateConverter.currentTime());
        userAdviceRepository.saveAndFlush(userAdviceEntity);*/
        String usrName =userAdviceEntity.getName();
        String usrEmail =userAdviceEntity.getEmail();


        return null;
    }

}
