package com.book.config;

import com.book.mapper.AuthMapper;
import com.book.service.Impl.UserAuthService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    AuthMapper mapper;

    @Resource
    UserAuthService service;

    @Resource
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(service)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/static/**", "/login", "/register", "/doRegister").permitAll()
                .antMatchers("/**").hasAnyRole("admin","user")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .successHandler(this::onAuthenticationSuccess)
                .failureHandler(this::onAuthenticationFailure)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login11")
                .and()
                .csrf().disable()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .tokenRepository(new JdbcTokenRepositoryImpl(){{setDataSource(dataSource);}})
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(this::authenticationDeny);
    }


    private void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("username", authentication.getName());
        session.setAttribute("role", mapper.getRoleByUsername(authentication.getName()));
        httpServletResponse.sendRedirect("index");
    }

    private void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("flag",1);
        httpServletResponse.sendRedirect("login");
    }

    private void authenticationDeny(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletRequest.getSession().setAttribute("flag",2);
        httpServletResponse.sendRedirect("login");
    }
}
