package com.mcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by vellerzheng on 2017/9/20.
 */
@Controller
public class MainController {

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

}
