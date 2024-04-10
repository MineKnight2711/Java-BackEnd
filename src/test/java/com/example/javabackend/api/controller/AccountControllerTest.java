
package com.example.javabackend.api.controller;
import com.example.javabackend.modules.account.DTO.AccountResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    private CreateMessageProvider createMessageProvider;

    @Test
    public void test() throws Exception {
        this.mockMvc.perform(post("/api/account/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }
    @Test
    public void testLoginSuccess() throws Exception {
        // Arrange
        String loginJson = "{\"username\":\"huynhphuocdat2@gmail.com\",\"password\":\"Aa@123456\"}";

        // Act
        MockHttpServletRequestBuilder request = post("/api/accounts/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson);

        // Assert
        mockMvc.perform(request)
                .andExpect(status().isOk())
//                .andExpect(content()
//                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect( jsonPath("$.status", is("Success")));
    }
}