package com.book.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModifyService {

    List<String> doModify(String uid, String changedName, String sex, String grade, String email, String password);
}
