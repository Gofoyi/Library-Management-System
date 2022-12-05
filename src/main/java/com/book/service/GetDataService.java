package com.book.service;


import com.book.entity.Book;
import com.book.entity.Student;

import java.util.List;

public interface GetDataService {
    List<Book> getBookListByTitle(String title);

    List<Book> getBorrowListByRole(String role);

    List<Book> getAllBookList();

    List<Book> getAvailBookList();

    List<Book> getUserBookList(String username);

    String getUidByUsername(String username);

    int getStudentCounts();

    int getBookCounts();

    int getAvailBookCounts();

    List<Student> getStudentList();

    List<Student> getStudentListByName(String name);

    int getStudentBookCounts(String username);

    String getStudentSexByName(String name);

    String getGradeByName(String name);

    String getEmailByUserName(String username);




}
