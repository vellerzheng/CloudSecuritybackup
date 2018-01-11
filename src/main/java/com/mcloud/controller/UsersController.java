package com.mcloud.controller;

import com.mcloud.model.UsersEntity;
import com.mcloud.repository.RoleRepository;
import com.mcloud.repository.UserRegisterRepository;
import com.mcloud.repository.UserRepository;
import com.mcloud.service.supportToolClass.shiro.verificationCode.ValidateCode;
import com.mcloud.service.users.UserLogin;
import com.mcloud.util.redis.RedisClusterClient;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    @Autowired
    private RedisClusterClient redisClusterClient;

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

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



        // 验证码处理
/*        String code = (String) session.getAttribute("validateCode");
        String submitCode = WebUtils.getCleanParam(request, "validateCode");
        if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(code,submitCode.toLowerCase())) {
            return "redirect:/";
        }*/
/*        redisClusterClient.set(userLogin.getUsername(),userLogin.getPassword(),86400);
        logger.info(redisClusterClient.get(userLogin.getUsername()).toString());*/

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
            return "clouds/users/admin/welcomeAdmin";
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


    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY,4,null);
        request.getSession().setAttribute("validateCode", verifyCode);
        response.setContentType("image/jpeg");
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true,
                Color.WHITE, Color.BLACK, null);
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }


}
