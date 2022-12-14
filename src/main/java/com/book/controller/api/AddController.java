package com.book.controller.api;

import com.book.service.AddInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AddController {

    @Resource
    AddInfoService service;

    @RequestMapping("/add-book")
    public String addBook(@RequestParam("Bn")String BookName, @RequestParam("desc")String desc, @RequestParam("price")Float price){
        service.addBookInfo(BookName, desc, price);
        return "redirect:/books";
    }

    @RequestMapping(value = "/add-borrow",method = RequestMethod.POST)
    public String addBorrow(@RequestParam("bid") int bid, @RequestParam("uid") String uid) {
        service.addBorrowInfo(bid, uid);
        return "redirect:/index";
    }
}
