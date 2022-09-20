package com.book.controller.page;

import com.book.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Resource
    RegisterService service;

    @RequestMapping("register")
    public String register(HttpSession session, Model model) {
        if (session.getAttribute("passwordFailure")!=null) {
            model.addAttribute("passwordFailure",session.getAttribute("passwordFailure"));
            session.removeAttribute("passwordFailure");
        }
        if (session.getAttribute("sexFailure")!=null){
            model.addAttribute("sexFailure",session.getAttribute("sexFailure"));
            session.removeAttribute("sexFailure");
        }
        if (session.getAttribute("uidFailure")!=null){
            model.addAttribute("uidFailure",session.getAttribute("uidFailure"));
            session.removeAttribute("uidFailure");
        }
        if (session.getAttribute("registerFailure")!=null){
            model.addAttribute("registerFailure",session.getAttribute("registerFailure"));
            session.removeAttribute("registerFailure");
        }
        if (session.getAttribute("nameFailure")!=null){
            model.addAttribute("nameFailure",session.getAttribute("nameFailure"));
            session.removeAttribute("nameFailure");
        }
        return "register";
    }

    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public String register(@RequestParam("uid") String uid,
                           @RequestParam("username") String name,
                           @RequestParam("sex") String sex,
                           @RequestParam("grade") String grade,
                           @RequestParam("password") String password, HttpSession session){
            if(service.register(uid, name, sex, grade, password, session))
                return "redirect:login";
            else
                return "redirect:register";
        }
}
