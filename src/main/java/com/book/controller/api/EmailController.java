package com.book.controller.api;

import com.book.service.VerifyService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EmailController {

    @Resource
    VerifyService service;

    @RequestMapping("/verify-code")
    public void verifyCode(@RequestParam("email") String email) {
        service.sendVerifyCode(email);
    }
}
