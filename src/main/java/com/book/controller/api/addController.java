package com.book.controller.api;

import com.book.service.AddInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class addController {

    @Resource
    AddInfoService service;

    @RequestMapping("/add-book")
    public String addBook(@RequestParam("Bn")String BookName, @RequestParam("desc")String desc, @RequestParam("price")Float price){
        service.addBookInfo(BookName, desc, price);
        return "redirect:/books";
    }
}
