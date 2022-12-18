package com.book.controller.api;

import com.book.SpringbootBookManageApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest(classes = {SpringbootBookManageApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ApiControllerTest {
    @Resource
    private MockMvc mvc;

    @Test
    @DisplayName("addBook接口测试")
    @WithMockUser(username = "张三",password = "123456",roles = {"admin"})
    @Transactional
    void addBook() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/add-book")
                        .param("Bn","SomeBook")
                        .param("desc","SomeDesc")
                        .param("price","20"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @DisplayName("addBorrow接口测试")
    @WithMockUser(username = "张三",password = "123456",roles = {"admin"})
    @Transactional
    void addBorrow() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/add-borrow")
                        .param("bid","17")
                        .param("uid","20251103117"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @DisplayName("addBorrow接口测试")
    @WithMockUser(username = "张七七",password = "810520440Zz",roles = {"user"})
    @Transactional
    void doModify() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/doModify")
                        .param("username","")
                        .param("sex","")
                        .param("grade","")
                        .param("email","")
                        .param("password","810520440Zz"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


}
