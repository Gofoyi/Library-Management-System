package com.book.controller.page;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("login")
    public String login(HttpSession session, Model model) {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();//获取spring security封装的当前用户信息对象
        if(!"anonymousUser".equals(object)){ //如果用户已登录，该对象是一个类型为String类型，内容为“anonymousUser”的字符串
            return "redirect:/index";//登录跳转至主页面
            }
        else {
            if (session.getAttribute("flag")!=null) {
                model.addAttribute("flag",session.getAttribute("flag"));
                session.removeAttribute("flag");
            } else {
                model.addAttribute("flag",0);
                session.removeAttribute("flag");
            }
            return "login";
        }
    }

}
