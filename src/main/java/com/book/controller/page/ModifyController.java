package com.book.controller.page;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

public class ModifyController {

    @Resource
    ModifyController service;

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
                         @RequestParam("username") String username,
                         @RequestParam("sex") String sex,
                         @RequestParam("grade") String grade,
                         @RequestParam("email") String email,HttpSession session
                         ){
            return "redirect:modify";
    }
}
