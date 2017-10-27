package com.mcloud.controller;

import com.mcloud.model.UsersEntity;
import com.mcloud.repository.RoleRepository;
import com.mcloud.repository.UserRegisterRepository;
import com.mcloud.repository.UserRepository;
import com.mcloud.service.users.UserLogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/clouds/users/register", method = RequestMethod.GET)
    public String getRegister() {
        return "clouds/users/register";
    }

    @RequestMapping(value = "/clouds/users/register", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("usersRegister") UsersEntity usersEntity){
        List<UsersEntity> userList=userRepository.findAll();
        for (UsersEntity uty: userList) {
            if(!(usersEntity==null) &&!uty.getUsername().equals(usersEntity.getUsername())&&!uty.getEmail().equals(usersEntity.getEmail())
                    &&!uty.getPhone().equals(usersEntity.getPhone())){

            }else{
                return "redirect:/clouds/users/register";
            }
        }
        String pwd = new SimpleHash("MD5",usersEntity.getPassword(),usersEntity.getUsername(),2).toHex();
        usersEntity.setPassword(pwd);
        System.out.println(usersEntity.getUsername());
        usersEntity.setUserRoleIdByRoleId(roleRepository.findOne(3));
        userRepository.saveAndFlush(usersEntity);
        return "redirect:/clouds/users/login";
    }

    @RequestMapping(value ="/clouds/users/login",method = RequestMethod.GET)
    public String getLogin(){ return "clouds/users/login"; }

    @RequestMapping(value = "/clouds/users/login", method = RequestMethod.POST)
    public String  authLoagin(HttpServletRequest request, HttpSession session, ModelMap modelMap, @ModelAttribute("login") UserLogin userLogin){

        Subject subject = SecurityUtils.getSubject();
        String checkpwd = new SimpleHash("MD5",userLogin.getPassword(),userLogin.getUsername(),2).toHex();
        UsernamePasswordToken token = new UsernamePasswordToken(userLogin.getUsername(),checkpwd);
        token.setRememberMe(true);

        //如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
        subject.login(token);

        if (subject.hasRole("user")) {
            UsersEntity uty = userRepository.findByUsernameEndsWith(userLogin.getUsername());
            modelMap.addAttribute("loginUser",uty);
            return "clouds/welcome";
        } else if (subject.hasRole("admin")) {
            UsersEntity utyAdmin = userRepository.findByUsernameEndsWith(userLogin.getUsername());
            modelMap.addAttribute("loginUser",utyAdmin);
            return "clouds/welcome";
        }

        return "redirect:/clouds/users/login";
    }


    // 本账户密码重置
    @RequestMapping(value = "/clouds/users/passwordReset/update", method = {RequestMethod.POST})
    public String passwordRest(String oldPassword, String password1) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        UsersEntity userlogin = userRepository.findByUsernameEndsWith(username);

        String oldPwdMd5= new SimpleHash("MD5",oldPassword,userlogin.getUsername(),2).toHex();
        String newpwd = new SimpleHash("MD5",password1,userlogin.getUsername(),2).toHex();
        if (!oldPwdMd5.equals(userlogin.getPassword())) {
            throw new Exception("旧密码不正确");
        } else {
            userlogin.setPassword(newpwd);
            userRepository.updateByName(username, userlogin.getPassword(),userlogin.getId());
        }

        return "redirect:/clouds/users/logout";
    }




}
