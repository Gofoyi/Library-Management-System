package com.book.service.Impl;


import com.book.mapper.AuthMapper;
import com.book.service.RegisterService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class RegisterServiceImpl implements RegisterService {

    //自动注入Spring框架
    @Resource
    AuthMapper mapper;


    @Transactional
    @Override
    public boolean register(String uid, String name, String sex, String grade, String password, HttpSession session) {
        //数据校验
        boolean flag = true;
        //对密码进行校验，检查密码必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间!
        if (!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$")){ //正则表达式
            session.setAttribute("passwordFailure",true);
            flag = false;
        }

        if (sex.equals("男")){} else if (sex.equals("女")){} else {
            session.setAttribute("sexFailure",true);
            flag = false;
        }
        if (!uid.matches("^\\d{11}$")){
            session.setAttribute("uidFailure",true);
            flag = false;
        }
        if (!name.matches("^[\\u4e00-\\u9fa5]*$")){
            session.setAttribute("nameFailure",true);
            flag = false;
        }
        if (!name.equals("") && mapper.existUsername(name)!=null){
            session.setAttribute("registerFailure",true);
            flag = false;
        }
        if (flag){
            //真正的注册，把数据存到数据库里
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (mapper.registerUser(uid, name, "user", encoder.encode(password)) <=0 ){
                throw new RuntimeException("User注册失败！");
            }
            if (mapper.addStudentInfo(uid, name, grade, sex) <= 0){
                throw new RuntimeException("Student注册失败！");
            }
        }
        return flag;
    }
}
