package com.book.service.Impl;


import com.book.entity.Student;
import com.book.mapper.AuthMapper;
import com.book.mapper.BookMapper;
import com.book.service.AddInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddInfoServiceImpl implements AddInfoService {

    @Resource
    BookMapper mapper;

    @Override
    public int addBorrowInfo(int bid, String uid) {
        return mapper.addBorrowInfo(bid, uid);
    }

    @Override
    public int addBookInfo(String BookName, String desc, Float price) {
        return mapper.addBookInfo(BookName, desc, price);
    }





}
