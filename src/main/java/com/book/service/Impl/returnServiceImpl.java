package com.book.service.Impl;

import com.book.mapper.AuthMapper;
import com.book.mapper.BookMapper;
import com.book.service.returnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class returnServiceImpl implements returnService {

    @Resource
    BookMapper mapper;

    @Resource
    AuthMapper authMapper;

    @Override
    public int deleteBorrowInfo(int id) {
        return mapper.deleteBorrowInfo(id);
    }

    @Override
    public int deleteBookInfo(int id) {
        return mapper.deleteBookInfo(id);
    }

    @Override
    public void deleteStudentInfo(String uid) {
        authMapper.DeleteUserTableInfo(uid);
    }
}
