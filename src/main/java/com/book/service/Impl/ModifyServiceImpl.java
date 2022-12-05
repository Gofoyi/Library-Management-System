package com.book.service.Impl;

import com.book.mapper.AuthMapper;
import com.book.service.ModifyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class ModifyServiceImpl implements ModifyService {

    @Resource
    AuthMapper mapper;


    boolean doModify(String name, String sex, HttpSession session) { //信息验证
        boolean flag = true;

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
        return flag;
    }

    @Transactional
    @Override
    public boolean Modify(String name, String sex, String grade, String email,String username,HttpSession session) {

        boolean flag = doModify(name, sex,session);
        if (flag){
            String uid = mapper.getUidByUsername(username);     //根据username获取uid
            mapper.modifyUserInfo(uid,name,email);
            if (mapper.modifyUserInfo(uid,name,email)<=0){       //数据存入user表
                throw new RuntimeException("user信息修改失败");
            }
            if (mapper.modifyStudentInfo(uid,name,grade,sex)<=0){       //数据存入student表
                throw new RuntimeException("student信息修改失败");
            }
        }
        return flag;
    }
}
