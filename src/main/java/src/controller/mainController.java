package src.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by vellerzheng on 2017/9/20.
 */
@Controller
public class mainController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public  String index() {return "index";}

    @RequestMapping(value ="/clouds/uploadfile", method = RequestMethod.GET)
    public String getUploadForm(ModelMap modelMap){
        return "clouds/uploadfile";
    }

    @RequestMapping(value ="/clouds/error", method = RequestMethod.GET)
    public String getError(){
        return "clouds/error";
    }

    @RequestMapping(value ="/clouds/home", method = RequestMethod.GET)
    public String getHome(){
        return "clouds/home";
    }



    @RequestMapping(value = "/clouds/login", method = RequestMethod.GET)
    public String getLogin() {
        return "clouds/login";
    }

    @RequestMapping(value = "/clouds/welcome", method = RequestMethod.GET)
    public String getwelcome() {
        return "clouds/welcome";
    }

}
