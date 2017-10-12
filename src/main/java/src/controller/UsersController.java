package src.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import src.service.users.Login;
import src.model.UsersEntity;
import src.repository.UserRegisterRepository;
import src.repository.UserRepository;

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
    public String  authLoagin(HttpServletRequest request, ModelMap modelMap, @ModelAttribute("login") Login login){
        List<UsersEntity> userList=userRepository.findAll();

        for (UsersEntity uty: userList) {
            if(uty.getUsername().equals(login.getUsername()) && uty.getPassword().equals(login.getPassword())){
                modelMap.addAttribute("loginUser",uty);
                return "clouds/welcome";
            }
        }

        return "redirect:/clouds/users/login";
    }
}
