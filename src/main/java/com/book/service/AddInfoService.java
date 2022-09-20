package com.book.service;


import com.book.entity.Student;

import java.util.List;

public interface AddInfoService {

    int addBorrowInfo(int bid, String uid);
    int addBookInfo(String BookName, String desc, Float price);

}
