package com.book.controller.page;


import com.book.entity.Book;
import com.book.entity.Pages;
import com.book.entity.Student;
import com.book.service.GetDataService;
import com.book.service.SepPageService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageController {

    @Resource
    GetDataService getDataService;

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

    @ModelAttribute(value = "uid")
    public String uid(@SessionAttribute("SPRING_SECURITY_CONTEXT") SecurityContext context){
        User user = (User) context.getAuthentication().getPrincipal();
        return String.valueOf(getDataService.getUidByUsername(user.getUsername()));
    }

    @ModelAttribute(value = "sex")
    public String sex(@SessionAttribute("SPRING_SECURITY_CONTEXT") SecurityContext context){
        User user = (User) context.getAuthentication().getPrincipal();
        return String.valueOf(getDataService.getStudentSexByName(user.getUsername()));
    }

    @ModelAttribute(value = "grade")
    public String grade(@SessionAttribute("SPRING_SECURITY_CONTEXT") SecurityContext context){
        User user = (User) context.getAuthentication().getPrincipal();
        return String.valueOf(getDataService.getGradeByName(user.getUsername()));
    }

    @ModelAttribute(value = "email")
    public String email(@SessionAttribute("SPRING_SECURITY_CONTEXT") SecurityContext context){
        User user = (User) context.getAuthentication().getPrincipal();
        return String.valueOf(getDataService.getEmailByUserName(user.getUsername()));
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
        model.addAttribute("studentcount", getDataService.getStudentCounts());
        model.addAttribute("bookcount", getDataService.getBookCounts());
        model.addAttribute("availbookcount", getDataService.getAvailBookCounts());
        model.addAttribute("StudentBookCounts",getDataService.getStudentBookCounts((String) session.getAttribute("username")));
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

    @RequestMapping("findBook")
    public String findBook(String title, Model model){
        List<Book> list = getDataService.getBookListByTitle(title);
        model.addAttribute("book_list", list);
        model.addAttribute("Num",1);
        model.addAttribute("TotalPageNum",1);
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

    @RequestMapping("findStudent")
    public String findStudent(String name, Model model){
        List<Student> list = getDataService.getStudentListByName(name);
        model.addAttribute("list", list);
        model.addAttribute("Num",1);
        model.addAttribute("TotalPageNum",1);
        return "student";
    }

    @RequestMapping("/add_borrow")
    public String addBorrow(Model model){
        model.addAttribute("student_List",getDataService.getStudentList());
        model.addAttribute("availBookList", getDataService.getAvailBookList());
        return "add_borrow";
    }

    @RequestMapping("/add_book")
    public String addBook(){
        return "add_book";
    }

    @RequestMapping("profile") //信息校验
    public String modify(HttpSession session, Model model){
        if (session.getAttribute("nameFailure")!=null){
            model.addAttribute("nameFailure",session.getAttribute("nameFailure"));
            session.removeAttribute("nameFailure");
        }
        if (session.getAttribute("sexFailure")!=null){
            model.addAttribute("sexFailure",session.getAttribute("sexFailure"));
            session.removeAttribute("sexFailure");
        }
        if (session.getAttribute("passwordFailure")!=null) {
            model.addAttribute("passwordFailure",session.getAttribute("passwordFailure"));
            session.removeAttribute("passwordFailure");
        }
        return "profile";
    }


}
