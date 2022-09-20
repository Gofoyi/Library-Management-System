package com.book.service.Impl;

import com.book.entity.Book;
import com.book.entity.Pages;
import com.book.entity.Student;
import com.book.mapper.AuthMapper;
import com.book.service.GetDataService;
import com.book.service.SepPageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SepPageServiceImpl implements SepPageService {

    @Resource
    AuthMapper mapper;

    @Resource
    GetDataService service;

    @Override
    public Pages<Student> getStudentPages(int SepPageNum) {
        return new Pages<>(mapper.getStudentList(),SepPageNum);
    }

    @Override
    public Pages<Book> getBookPages(int SepPageNum) {
        return new Pages<>(service.getAllBookList(),SepPageNum);
    }

    @Override
    public Pages<Book> getUserBookPages(int SepPageNum, String username) {
        return new Pages<>(service.getUserBookList(username),SepPageNum);
    }

}
