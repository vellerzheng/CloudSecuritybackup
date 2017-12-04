package com.mcloud.controller;


import com.mcloud.model.UserAdviceEntity;
import com.mcloud.repository.UserAdviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController
{
    @Autowired
    UserAdviceRepository userAdviceRepository;
    //无条件查询
    @RequestMapping(value = "/clouds/users/admin/findNoQuery/AdviceInfo")
    public String showAdviceInfo(ModelMap modelMap, @RequestParam(value = "page",defaultValue = "0") Integer page,
                                 @RequestParam(value = "size",defaultValue = "5") Integer size){

        List<UserAdviceEntity> adviceLists=new ArrayList<>();
        Sort sort =new Sort(Sort.Direction.DESC,"id");
        Pageable pageable =new PageRequest(0,5,sort);
        Page<UserAdviceEntity> pageAdviceEty =userAdviceRepository.findAll(pageable);

        for (UserAdviceEntity vec : pageAdviceEty) {
                adviceLists.add(vec);
        }
        modelMap.addAttribute("pageAdviceEty",pageAdviceEty);
        modelMap.addAttribute("adviceLists",adviceLists);
        return "clouds/users/admin/adviceInfo";
    }

}
