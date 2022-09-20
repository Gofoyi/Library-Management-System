package com.book.service;

import com.book.entity.Book;
import com.book.entity.Pages;
import com.book.entity.Student;

public interface SepPageService {
    Pages<Student> getStudentPages(int SepPageNum);
    Pages<Book> getBookPages(int SepPageNum);
    Pages<Book> getUserBookPages(int SepPageNum, String username);
}
