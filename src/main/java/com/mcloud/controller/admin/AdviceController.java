package com.mcloud.controller.admin;


import com.mcloud.model.UserAdviceEntity;
import com.mcloud.model.UsersEntity;
import com.mcloud.model.common.Pager;
import com.mcloud.model.common.UsersPage;
import com.mcloud.repository.UserAdviceRepository;
import com.mcloud.util.common.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clouds/users/admin/adviceInfo")
public class AdviceController
{
    @Autowired
    UserAdviceRepository userAdviceRepository;

    @Autowired
    UserUtils userUtils;

    //无条件查询
    @RequestMapping(value = "/list")
    public ModelAndView showAdviceInfo(@ModelAttribute("pageAttribute") Pager pager){

        ModelAndView mv = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        UsersEntity loginUser = userUtils.getUsersEntity(username);

      //  int FirstResult = (pager.getNowPageNo() - 1) * pager.getSizePerPage();
        int count = (int)userAdviceRepository.count();
        pager.setTotalCount(count);

        int firstResult = pager.getNowPageNo() - 1;
        int sizePerPage = pager.getSizePerPage();

      //  sizePerPage = sizePerPage > count ? count:sizePerPage;

        List<UserAdviceEntity> adviceLists=new ArrayList<>();
        Sort sort =new Sort(Sort.Direction.DESC,"id");
        Pageable pageable =new PageRequest(firstResult,sizePerPage,sort);
        Page<UserAdviceEntity> pageAdviceEty =userAdviceRepository.findAll(pageable);

        for (UserAdviceEntity vec : pageAdviceEty) {
                adviceLists.add(vec);
        }

        mv.addObject("loginUser",loginUser);
        mv.addObject("pageAdviceEty",pageAdviceEty);
        mv.addObject("adviceLists",adviceLists);
        mv.addObject("pager", pager);
        mv.setViewName("clouds/users/admin/adviceInfo");
        return mv;
    }

}
