package com.book.service;

import org.springframework.stereotype.Service;

@Service
public interface VerifyService {
    void sendVerifyCode(String email);
    boolean doEmailVerify(String email, String code);
}
