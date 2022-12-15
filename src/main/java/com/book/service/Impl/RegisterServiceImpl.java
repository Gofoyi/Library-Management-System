package com.book.service.Impl;


import com.book.mapper.AuthMapper;
import com.book.service.RegisterService;
import com.book.service.VerifyService;
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
    @Resource
    VerifyService service;



    boolean doDataVerify(String uid,String name, String password, HttpSession session){
        //数据校验
        boolean flag = true;
        //对密码进行校验，检查密码必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-18之间!
        if (!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,18}$")){ //正则表达式
            session.setAttribute("passwordFailure",true);
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
        return flag;
    }

    @Transactional
    @Override
    public boolean register(String uid,String name, String password, HttpSession session) {
        boolean flag = doDataVerify(uid,name,password, session);
        if (flag){
            //把数据存到数据库里
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (mapper.registerUser(uid,name, "user", encoder.encode(password)) <=0 ){
                throw new RuntimeException("User注册失败！");
            }
            if (mapper.addStudentInfo(uid,name) <= 0){
                throw new RuntimeException("Student注册失败！");
            }
        }
        return flag;
    }

    @Override
    public boolean doEmailRegister(String uid,String name,String password, String email, String code, HttpSession session) {
        boolean flag = doDataVerify(uid,name,password, session);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //单独验证邮箱
        if (flag && service.doEmailVerify(email, code)){
            if (mapper.registerEmailedUser(uid,name, "user", encoder.encode(password),email) <=0 ){
                throw new RuntimeException("User注册失败！");
            }
            if (mapper.addStudentInfo(uid,name) <= 0){
                throw new RuntimeException("Student注册失败！");
            }
        }
        return flag && service.doEmailVerify(email, code);
    }

    @Override
    public boolean isFillInfo(String name){
        boolean flag=false;
        if(mapper.getStudentSexByName(name)==null || mapper.getGradeByName(name)==null)
            flag=true;
        return flag;
    }
}
