package com.book.service.Impl;

import com.book.mapper.AuthMapper;
import com.book.service.ModifyService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ModifyServiceImpl implements ModifyService {

    @Resource
    AuthMapper authMapper;

    @Transactional
    @Override
    public List<String> doModify(String uid, String changedName, String sex, String grade, String email, String password) {
        //检查是否合格
        List<String> errorList = new ArrayList<>();
        if (!changedName.equals("")) {
            authMapper.modifyUsernameInfo(changedName, uid);
            authMapper.modifyUserTableUsernameInfo(changedName,uid);
            if (!changedName.matches("^[\\u4e00-\\u9fa5]*$")){
                errorList.add("nameFailure");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        if (!sex.equals("")) {
            try {
                authMapper.modifySexInfo(sex, uid);
            } catch (Exception e){
                errorList.add("sexFailure");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        if (!grade.equals("")) {
            authMapper.modifyGradeInfo(grade, uid);
        }
        if (!email.equals("")) {
            authMapper.modifyEmailInfo(email, uid);
        }
        if (!password.equals("")) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            authMapper.modifyPasswordInfo(encoder.encode(password), uid);
            if (!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,18}$")){
                errorList.add("passwordFailure");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        return errorList;
    }

}
