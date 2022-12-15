package com.book.service;

import javax.servlet.http.HttpSession;

public interface RegisterService {
    boolean register(String uid, String name,String password, HttpSession session);
    boolean doEmailRegister(String uid, String name, String password, String email, String code,HttpSession session);
}
