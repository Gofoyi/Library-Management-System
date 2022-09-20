package com.book.service.Impl;

import com.book.mapper.AuthMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAuthService implements UserDetailsService {

    @Resource
    AuthMapper mapper;


    //登陆操作
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String password = mapper.getPasswordByUsername(s);
        String role = mapper.getRoleByUsername(s);
        if (password == null) throw new UsernameNotFoundException("登陆失败，用户名或密码错误！");
        return User
                    .withUsername(s)
                    .password(password)
                    .roles(role)
                    .build();

    }
}
