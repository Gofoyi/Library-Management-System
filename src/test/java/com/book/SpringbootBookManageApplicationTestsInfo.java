package com.book;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.Scanner;

@SpringBootTest
class SpringbootBookManageApplicationTestsInfo {

    @Test
    void tes1() {
        Random random = new Random();
        int code = random.nextInt(899999)+100000;
        System.out.println(code);
    }

}
