package com.book.controller.api;

import com.book.service.GetDataService;
import com.book.service.ModifyService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ModifyController {

    @Resource
    GetDataService getDataService;

    @Resource
    ModifyService modifyService;

    @RequestMapping(value = "/doModify",method = RequestMethod.POST)
    public String modify(@RequestParam("username") String username,
                         @RequestParam("sex") String sex,
                         @RequestParam("grade") String grade,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @SessionAttribute("SPRING_SECURITY_CONTEXT") SecurityContext context, HttpSession session){
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        String unChangeUsername = user.getUsername();
        String uid = getDataService.getUidByUsername(unChangeUsername); //根据username获取uid
        //获取错误信息列表
        List<String> errorList = modifyService.doModify(uid,username,sex,grade,email,password);

        //错误
        if (!errorList.isEmpty()){
            //使用session传递错误信息
            for (String e : errorList) {
                session.setAttribute(e,e);
            }
            return "redirect:profile";
        }

        //成功
        if (!username.equals(""))
            return "redirect:logout";
        else if (!password.equals(""))
            return "redirect:logout";
        else
            return "redirect:profile";
    }
}
