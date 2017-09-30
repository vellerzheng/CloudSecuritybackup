package src.controller;

/**
 * Created by vellerzheng on 2017/9/29.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import src.model.UsersEntity;
import src.repository.UserRegisterRepository;
import src.repository.UserRepository;

import java.util.List;


@Controller

public class RegistrationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRegisterRepository userRegisterRepository;

    @RequestMapping(value = "/clouds/register/add", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("userRegister") UsersEntity usersEntity){
        List<UsersEntity> userList=userRepository.findAll();
        for (UsersEntity uty: userList) {
            if(!usersEntity.getEmail().isEmpty() &&!uty.getUsername().equals(usersEntity.getUsername())&&!uty.getEmail().equals(usersEntity.getEmail())
                    &&!uty.getPhone().equals(usersEntity.getPhone())){

            }else{
                return "redirect:/clouds/register";
            }
        }
            System.out.println(usersEntity.getUsername());
            userRepository.saveAndFlush(usersEntity);
            return "clouds/welcome";
    }

}
