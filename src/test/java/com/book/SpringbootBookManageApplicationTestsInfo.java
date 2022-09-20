package com.book;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

@SpringBootTest
class SpringbootBookManageApplicationTestsInfo {

    @Test
    void tes1() {
        String sex = "男";
        if (sex.equals("男")||sex.equals("女")){
            System.out.println("no!");
        }
    }

}
