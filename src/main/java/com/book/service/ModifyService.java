package com.book.service;

import javax.servlet.http.HttpSession;

public interface ModifyService {
    boolean Modify(String name, String sex, String grade,String email,String username,HttpSession session);
}
