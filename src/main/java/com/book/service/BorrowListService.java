package com.book.service;


import com.book.entity.Book;

import java.util.List;

public interface BorrowListService {

    List<Book> getBorrowListByRole(String role);

    List<Book> getAllBookList();

    List<Book> getAvailBookList();

    List<Book> getUserBookList(String username);
}
