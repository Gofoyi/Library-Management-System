package com.book.service;

import javax.servlet.http.HttpSession;

public interface RegisterService {
    boolean register(String uid, String name, String sex, String grade, String password, HttpSession session);
}
