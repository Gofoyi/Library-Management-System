package com.book.controller.page;


import com.book.entity.Book;
import com.book.entity.Pages;
import com.book.entity.Student;
import com.book.service.AddInfoService;
import com.book.service.BorrowListService;
import com.book.service.SepPageService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @Resource
    BorrowListService borrowListService;

    @Resource
    AddInfoService addInfoService;

    @Resource
    SepPageService pageService;

    @ModelAttribute(value = "role")
    public String role(@SessionAttribute("SPRING_SECURITY_CONTEXT") SecurityContext context){
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        return String.valueOf(user.getAuthorities());
    }

    @ModelAttribute(value = "username")
    public String username(@SessionAttribute("SPRING_SECURITY_CONTEXT") SecurityContext context){
        User user = (User) context.getAuthentication().getPrincipal();
        return String.valueOf(user.getUsername());
    }


    @RequestMapping("index")
    public String index(@RequestParam(value = "PageNum", defaultValue = "1") int PageNum, Model model, HttpSession session){
        //分页信息
        Pages<Book> Allpages = pageService.getBookPages(10);
        Pages<Book> Userpages = pageService.getUserBookPages(10, (String) session.getAttribute("username"));
        model.addAttribute("book_list_pages", Allpages);
        model.addAttribute("UserBook_list_pages", Userpages);

        model.addAttribute("Num",PageNum);

        //顶部信息
        model.addAttribute("studentcount", addInfoService.getStudentCounts());
        model.addAttribute("bookcount", addInfoService.getBookCounts());
        model.addAttribute("availbookcount", addInfoService.getAvailBookCounts());
        model.addAttribute("StudentBookCounts",addInfoService.getStudentBookCounts((String) session.getAttribute("username")));
        return "index";
    }

    @RequestMapping("books")
    public String book(@RequestParam(value = "PageNum", defaultValue = "1") int PageNum, Model model){
        Pages<Book> pages = pageService.getBookPages(10);
        model.addAttribute("book_list", pages.getSepPage(PageNum));
        model.addAttribute("Num",PageNum);
        model.addAttribute("TotalPageNum",pages.getTotalPageNum());
        return "books";
    }

    @RequestMapping("student")
    public String student(@RequestParam(value = "PageNum", defaultValue = "1") int PageNum, Model model){
        Pages<Student> pages = pageService.getStudentPages(10);
        model.addAttribute("list",pages.getSepPage(PageNum));
        model.addAttribute("Num",PageNum);
        model.addAttribute("TotalPageNum",pages.getTotalPageNum());
        return "student";
    }

    @RequestMapping("/add_borrow")
    public String addBorrow(Model model){
        model.addAttribute("student_List",addInfoService.getStudentList());
        model.addAttribute("availBookList",borrowListService.getAvailBookList());
        return "add_borrow";
    }

    @RequestMapping("/add_book")
    public String addBook(){
        return "add_book";
    }

}
