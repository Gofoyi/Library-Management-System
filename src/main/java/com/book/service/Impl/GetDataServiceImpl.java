package com.book.service.Impl;


import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;
import com.book.mapper.AuthMapper;
import com.book.mapper.BookMapper;
import com.book.service.GetDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class GetDataServiceImpl implements GetDataService {

    @Resource
    BookMapper mapper;

    @Resource
    AuthMapper authMapper;

    public List<Book> getBookListByTitle(String title){
        return mapper.getBookListByTitle(title);
    }

    @Override
    public List<Book> getBorrowListByRole(String role) {
        return null;
    }

    @Override
    public List<Book> getAllBookList() {

        //对数据库查询，得到Book类的一个List
        List<Book> list = mapper.getAllBookList();

        //对数据进行操作
        for (Book book : list) {
            if (book.getBorrow() == null) {
                book.setBorrow(new Borrow());//Borrow对象里面的数据是空的
                book.setStatus(false);//表示这本书没有借出
            }
        }
        return list;
    }

    @Override
    public List<Book> getAvailBookList() {
        return mapper.getAvailBookList();
    }

    @Override
    public List<Book> getUserBookList(String username) {
        List<Book> list = mapper.getAllBookList();
        list.removeIf(book -> book.getBorrow() == null || !book.getBorrow().getStudent_Name().equals(username));
        return list;
    }

    public String getUidByUsername(String username){
        return authMapper.getUidByUsername(username);
    }

    @Override
    public int getStudentCounts() {
        return authMapper.getStudentCounts();
    }

    @Override
    public int getBookCounts() {
        return mapper.getBookCounts();
    }

    @Override
    public int getAvailBookCounts() {
        return mapper.getAvailBookCounts();
    }

    @Override
    public List<Student> getStudentList() {
        return authMapper.getStudentList();
    }

    @Override
    public List<Student> getStudentListByName(String name) {
        return authMapper.getStudentListByName(name);
    }

    @Override
    public int getStudentBookCounts(String username) {
        return mapper.getStudentBookCounts(authMapper.getUidByUsername(username));
    }

    @Override
    public String getStudentSexByName(String name) {
        return authMapper.getStudentSexByName(name);
    }

    @Override
    public String getGradeByName(String name) {
        return  authMapper.getGradeByName(name);
    }

    @Override
    public String getEmailByUserName(String username) {
        return authMapper.getEmailByUsername(username);
    }




}
