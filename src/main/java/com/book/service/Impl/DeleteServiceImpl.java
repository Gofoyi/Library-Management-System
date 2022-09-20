package com.book.service.Impl;

import com.book.mapper.AuthMapper;
import com.book.mapper.BookMapper;
import com.book.service.DeleteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeleteServiceImpl implements DeleteService {

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
