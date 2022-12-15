package com.book.service;

import com.book.SpringbootBookManageApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {SpringbootBookManageApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VerifyServiceImplTest {

    @Resource
    VerifyService service;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    private String email = "810520440@qq.com";

    @Test
    @DisplayName("发送邮箱验证码功能测试")
    void sendVerifyCode() {
        service.sendVerifyCode(email);
    }

    @Test
    @DisplayName("验证验证码功能测试")
    void doEmailVerify() {
        Random random = new Random();
        int code = random.nextInt(899999)+100000;
        stringRedisTemplate.opsForValue().set("verify:code:"+email,code+"" ,3, TimeUnit.MINUTES);
        service.doEmailVerify(email, String.valueOf(code));
    }
}