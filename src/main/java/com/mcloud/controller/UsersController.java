package com.mcloud.controller;

import com.mcloud.model.UsersEntity;
import com.mcloud.repository.UserRegisterRepository;
import com.mcloud.repository.UserRepository;
import com.mcloud.service.users.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * Created by vellerzheng on 2017/10/2.
 */
@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRegisterRepository userRegisterRepository;

    @RequestMapping(value = "/clouds/users/register", method = RequestMethod.GET)
    public String getRegister() {
        return "clouds/users/register";
    }

    @RequestMapping(value = "/clouds/users/register/addP", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("userRegister") UsersEntity usersEntity){
        List<UsersEntity> userList=userRepository.findAll();
        for (UsersEntity uty: userList) {
            if(!usersEntity.getEmail().isEmpty() &&!uty.getUsername().equals(usersEntity.getUsername())&&!uty.getEmail().equals(usersEntity.getEmail())
                    &&!uty.getPhone().equals(usersEntity.getPhone())){

            }else{
                return "redirect:/clouds/users/register";
            }
        }
        System.out.println(usersEntity.getUsername());
        userRepository.saveAndFlush(usersEntity);
        return "clouds/welcome";
    }

    @RequestMapping(value ="/clouds/users/login",method = RequestMethod.GET)
    public String getLogin(){ return "clouds/users/login"; }

    @RequestMapping(value = "/clouds/users/login/auth", method = RequestMethod.POST)
    public String  authLoagin(HttpServletRequest request, ModelMap modelMap, @ModelAttribute("login") UserLogin userLogin){
        List<UsersEntity> userList=userRepository.findAll();

        for (UsersEntity uty: userList) {
            if(uty.getUsername().equals(userLogin.getUsername()) && uty.getPassword().equals(userLogin.getPassword())){
                modelMap.addAttribute("loginUser",uty);
                return "clouds/welcome";
            }
        }



/*         shiro 实现登陆 */
/*
        UsernamePasswordToken token = new UsernamePasswordToken(userLogin.getUsername(),userLogin.getPassword());
        Subject subject = SecurityUtils.getSubject();

        //如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
        subject.login(token);

        if (subject.hasRole("usr")) {
            UsersEntity uty = userRepository.findByUsernameEndsWith(userLogin.getUsername());
            modelMap.addAttribute("loginUser",uty);
            return "redirect:/clouds/welcome";
        } else if (subject.hasRole("admin")) {
            return "redirect:/clouds/users/admin/admin";
        }
*/

        return "redirect:/clouds/users/login";
    }
}
