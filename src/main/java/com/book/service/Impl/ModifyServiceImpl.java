package com.book.service.Impl;

import com.book.mapper.AuthMapper;
import com.book.service.ModifyService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

public class ModifyServiceImpl implements ModifyService {

    @Resource
    AuthMapper mapper;

    @Transactional
    @Override
    public boolean Modify(String uid, String name, String sex, String grade, String email, HttpSession session) {
        boolean flag = true;

        if (!uid.matches("^\\d{11}$")){
            session.setAttribute("uidFailure",true);
            flag = false;
        }
        if (!name.matches("^[\\u4e00-\\u9fa5]*$")){
            session.setAttribute("nameFailure",true);
            flag = false;
        }
        if (sex.equals("男")){} else if (sex.equals("女")){} else {
            session.setAttribute("sexFailure",true);
            flag = false;
        }
        if (!name.equals("") && mapper.existUsername(name)!=null){
            session.setAttribute("modifyFailure",true);
            flag = false;
        }
        if (flag){
            if (mapper.modifyStudentInfo(uid, name, sex,grade) <= 0){
                throw new RuntimeException("修改失败！");
            }
        }
        return flag;
    }
}
