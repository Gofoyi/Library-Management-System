package com.book;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;
import java.util.Scanner;

@SpringBootTest
class SpringbootBookManageApplicationTestsInfo {

    @Test
    void tes1() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("810520440Zh"));
    }


}
