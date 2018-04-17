package com.mcloud.controller.desk;

import com.mcloud.model.*;
import com.mcloud.repository.*;
import com.mcloud.util.common.CustomDateConverter;
import com.mcloud.util.common.InfoJson;
import com.mcloud.util.common.UserUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class ConfigController {
    @Autowired
    UserUtils userUtils;
    @Autowired
    ConfAliyunRespository confAliyunRespository;
    @Autowired
    ConfNeteaseRespository confNeteaseRespository;
    @Autowired
    ConfQcloudRespository confQcloudRespository;
    @Autowired
    ConfQiniuRespository confQiniuRespository;
    @Autowired
    ConfUpyunRespository confUpyunRespository;


    @RequestMapping(value = "clouds/users/default/cloudConf/aliyunConfig")
    public String requestAliyunConfig(ModelMap modelMap) {
        UsersEntity loginUser =userUtils.getUserNameByShiro();
        modelMap.addAttribute("loginUser",loginUser);
        return "clouds/users/default/cloudConf/aliyunConfig";
    }

    @RequestMapping(value = "clouds/users/default/cloudConf/neteaseConfig")
    public String requestNeteaseConfig(ModelMap modelMap) {
        UsersEntity loginUser =userUtils.getUserNameByShiro();
        modelMap.addAttribute("loginUser",loginUser);
        return "clouds/users/default/cloudConf/neteaseConfig";
    }

    @RequestMapping(value = "clouds/users/default/cloudConf/qiniuConfig")
    public String requestqiniuConfig(ModelMap modelMap) {
        UsersEntity loginUser =userUtils.getUserNameByShiro();
        modelMap.addAttribute("loginUser",loginUser);
        return "clouds/users/default/cloudConf/qiniuConfig";
    }

    @RequestMapping(value = "clouds/users/default/cloudConf/qcloudConfig")
    public String requestQCloudConfig(ModelMap modelMap) {
        UsersEntity loginUser =userUtils.getUserNameByShiro();
        modelMap.addAttribute("loginUser",loginUser);
        return "clouds/users/default/cloudConf/qCloudConfig";
    }

    @RequestMapping(value = "clouds/users/default/cloudConf/upyunConfig")
    public String requestUpyunConfig(ModelMap modelMap) {
        UsersEntity loginUser =userUtils.getUserNameByShiro();
        modelMap.addAttribute("loginUser",loginUser);
        return "clouds/users/default/cloudConf/upyunConfig";
    }



    @RequestMapping(value = "/js/AJAX.js/aliyunConfig",method = RequestMethod.POST)
    @ResponseBody
    public InfoJson confAliyunCloud(@RequestBody ConfAliyunEntity confAliyunEntity){

        UsersEntity usersEntity=userUtils.getUserNameByShiro();
        confAliyunEntity.setUserId(usersEntity.getId());
        confAliyunEntity.setCreator(usersEntity.getUsername());
        confAliyunEntity.setCreatetime(CustomDateConverter.currentTime());
        confAliyunEntity.setUpdatetime(CustomDateConverter.currentTime());
        confAliyunRespository.save(confAliyunEntity);
        return InfoJson.getSucc("上传成功");
    }

    @RequestMapping(value = "/js/AJAX.js/neteaseConfig",method = RequestMethod.POST)
    @ResponseBody
    public InfoJson confneteaseCloud(@RequestBody ConfNeteaseEntity confNeteaseEntity){

        UsersEntity usersEntity=userUtils.getUserNameByShiro();
        confNeteaseEntity.setUserId(usersEntity.getId());
        confNeteaseEntity.setCreator(usersEntity.getUsername());
        confNeteaseEntity.setCreatetime(CustomDateConverter.currentTime());
        confNeteaseEntity.setUpdatetime(CustomDateConverter.currentTime());
        confNeteaseRespository.save(confNeteaseEntity);
        return InfoJson.getSucc("上传成功");
    }

    @RequestMapping(value = "/js/AJAX.js/qcloudConfig",method = RequestMethod.POST)
    @ResponseBody
    public InfoJson confqCloudCloud(@RequestBody ConfQcloudEntity confQcloudEntity){

        UsersEntity usersEntity=userUtils.getUserNameByShiro();
        confQcloudEntity.setUserId(usersEntity.getId());
        confQcloudEntity.setCreator(usersEntity.getUsername());
        confQcloudEntity.setCreatetime(CustomDateConverter.currentTime());
        confQcloudEntity.setUpdatetime(CustomDateConverter.currentTime());
        confQcloudRespository.save(confQcloudEntity);
        return InfoJson.getSucc("上传成功");
    }

    @RequestMapping(value = "/js/AJAX.js/qiniuConfig",method = RequestMethod.POST)
    @ResponseBody
    public InfoJson confQiniuCloud(@RequestBody ConfQiniuEntity confQiniuEntity){

        UsersEntity usersEntity=userUtils.getUserNameByShiro();
        confQiniuEntity.setUserId(usersEntity.getId());
        confQiniuEntity.setCreator(usersEntity.getUsername());
        confQiniuEntity.setCreatetime(CustomDateConverter.currentTime());
        confQiniuEntity.setUpdatetime(CustomDateConverter.currentTime());
        confQiniuRespository.save(confQiniuEntity);
        return InfoJson.getSucc("上传成功");
    }


    @RequestMapping(value = "/js/AJAX.js/upyunConfig",method = RequestMethod.POST)
    @ResponseBody
    public InfoJson confUpyunCloud(@RequestBody ConfUpyunEntity confUpyunEntity){

        UsersEntity usersEntity=userUtils.getUserNameByShiro();
        confUpyunEntity.setUserId(usersEntity.getId());
        confUpyunEntity.setCreator(usersEntity.getUsername());
        confUpyunEntity.setCreatetime(CustomDateConverter.currentTime());
        confUpyunEntity.setUpdatetime(CustomDateConverter.currentTime());
        confUpyunRespository.save(confUpyunEntity);
        return InfoJson.getSucc("上传成功");
    }


}
