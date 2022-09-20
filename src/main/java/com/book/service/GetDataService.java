package com.book.service;


import com.book.entity.Book;
import com.book.entity.Student;

import java.util.List;

public interface GetDataService {

    List<Book> getBorrowListByRole(String role);

    List<Book> getAllBookList();

    List<Book> getAvailBookList();

    List<Book> getUserBookList(String username);

    int getStudentCounts();

    int getBookCounts();

    int getAvailBookCounts();

    List<Student> getStudentList();

    int getStudentBookCounts(String username);
}
