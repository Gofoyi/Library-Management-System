package com.book.controller.page;

import com.book.service.ModifyService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

public class ModifyController {

    @Resource
    ModifyService service;

    @RequestMapping("profile")
    public String modify(HttpSession session, Model model){
        if (session.getAttribute("modifyFailure")!=null){
            model.addAttribute("registerFailure",session.getAttribute("modifyFailure"));
            session.removeAttribute("modifyFailure");
        }
        if (session.getAttribute("nameFailure")!=null){
            model.addAttribute("nameFailure",session.getAttribute("nameFailure"));
            session.removeAttribute("nameFailure");
        }
        if (session.getAttribute("uidFailure")!=null){
            model.addAttribute("sexFailure",session.getAttribute("uidFailure"));
            session.removeAttribute("uidFailure");
        }
        if (session.getAttribute("sexFailure")!=null){
            model.addAttribute("uidFailure",session.getAttribute("sexFailure"));
            session.removeAttribute("sexFailure");
        }
        return "modify";
    }

    @RequestMapping(value = "/doModify",method = RequestMethod.POST)
    public String modify(@RequestParam("uid") String uid,
                          @RequestParam("username") String name,
                          @RequestParam("sex") String sex,
                          @RequestParam("grade") String grade,
                          @RequestParam("email") String email, HttpSession session
                         ){
        if(service.Modify(uid, name, sex, grade, email, session)) {
            return "redirect:/index";
        }
        else
            return "redirect:/profile";
    }
}
