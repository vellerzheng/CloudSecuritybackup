package src.controller;

/**
 * Created by vellerzheng on 2017/9/29.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import src.model.UserEntity;
import src.model.UserRegistrationEntity;
import src.repository.UserRegisterRepository;
import src.repository.UserRepository;

@Controller

public class RegistrationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRegisterRepository userRegisterRepository;

    @RequestMapping(value = "/clouds/register/add", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("userRegister") UserEntity userEntity,@ModelAttribute("userRegister2") UserRegistrationEntity userRegistrationEntity){
        System.out.println(userEntity.getUsername());
        userRepository.saveAndFlush(userEntity);
        userRegisterRepository.saveAndFlush(userRegistrationEntity);
        return "redirect:clouds/welcome";
    }

}
