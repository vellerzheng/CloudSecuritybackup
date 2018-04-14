package com.mcloud.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
    @RequestMapping(value ="/clouds/users/admin", method = RequestMethod.GET)
    public String getAdminSystemManager(){
        return "/clouds/users/admin/admin";
    }
}
