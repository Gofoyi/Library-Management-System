package com.book.service;

public interface DeleteService {

    int deleteBorrowInfo(int id);
    int deleteBookInfo(int id);
    void deleteStudentInfo(String uid);
}
