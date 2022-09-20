package com.book.controller.api;

import com.book.service.returnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class deleteController {

    @Resource
    returnService service;

    @RequestMapping(value = "/return_book", params = {"id"})
    public String return_Book(@RequestParam("id") int id){
        service.deleteBorrowInfo(id);
        return "redirect:index";
    }

    @RequestMapping(value = "/delete_book", params = {"bid"})
    public String deleteBook(@RequestParam("bid") int id){
        service.deleteBookInfo(id);
        return "redirect:books";
    }

    @RequestMapping(value = "/delete_student", params = {"uid"})
    public String deleteStudent(@RequestParam("uid") String id){
        service.deleteStudentInfo(id);
        return "redirect:student";
    }
}
