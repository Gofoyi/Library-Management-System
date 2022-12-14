package com.book.service;

import com.book.SpringbootBookManageApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {SpringbootBookManageApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegisterServiceTest {

    @Resource
    RegisterService service;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    private String email = "810520440@qq.com";

    @Test
    @DisplayName("数据校验与邮箱注册功能测试")
    @Transactional
    void doEmailRegister() {
        Random random = new Random();
        int code = random.nextInt(899999)+100000;
        stringRedisTemplate.opsForValue().set("verify:code:"+email,code+"" ,3, TimeUnit.MINUTES);
        service.doEmailRegister("2011552143","TestName","男","2020","123456Zz",email, String.valueOf(code),null);
    }
}