package com.book.controller.page;

import com.book.SpringbootBookManageApplication;
import org.apache.juli.logging.Log;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import java.util.logging.Logger;


@SpringBootTest(classes = {SpringbootBookManageApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.DisplayName.class)
@AutoConfigureMockMvc
class PageControllerTest {

    @Resource
    private MockMvc mvc;

//    @BeforeEach
//    void init(){
//        System.out.println("111111111");
//    }

    @Test
    @DisplayName("1-init")
    @WithMockUser(username = "张三",password = "123456",roles = {"admin"})
    void start() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/index")).andReturn();
    }

    @RepeatedTest(5)
    @DisplayName("2-index页面测试")
    @WithMockUser(username = "张三",password = "123456",roles = {"admin"})
    void index() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @RepeatedTest(5)
    @DisplayName("3-book页面测试")
    @WithMockUser(username = "张三",password = "123456",roles = {"admin"})
    public void book() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @RepeatedTest(5)
    @DisplayName("4-student页面测试")
    @WithMockUser(username = "张三",password = "123456",roles = {"admin"})
    void student() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/student"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @RepeatedTest(5)
    @DisplayName("5-添加借阅信息页面测试")
    @WithMockUser(username = "张三",password = "123456",roles = {"admin"})
    void addBorrow() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/add_borrow"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @RepeatedTest(5)
    @DisplayName("6-添加书籍信息页面测试")
    @WithMockUser(username = "张三",password = "123456",roles = {"admin"})
    void add_book() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/add_book"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @RepeatedTest(5)
    @DisplayName("7-findStudent搜索测试")
    @WithMockUser(username = "张三",password = "123456",roles = {"admin"})
    void findStudent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/findStudent"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @RepeatedTest(5)
    @DisplayName("8-findBook搜索测试")
    @WithMockUser(username = "张三",password = "123456",roles = {"admin"})
    void findBook() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/findBook"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @DisplayName("9-Profile页面测试")
    @WithMockUser(username = "张四",password = "123456",roles = {"user"})
    void profile() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/profile"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


}