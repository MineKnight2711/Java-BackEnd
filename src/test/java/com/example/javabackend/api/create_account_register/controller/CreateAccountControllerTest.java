package com.example.javabackend.api.create_account_register.controller;

import com.example.javabackend.modules.account.DTO.AccountResponseDto;
import com.example.javabackend.modules.account.DTO.AccountsDTO;
import com.example.javabackend.modules.account.controller.AccountController;
import com.example.javabackend.modules.account.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class CreateAccountControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }
    @Test
    public void testCreateAccountWithInvalidBirthday() throws Exception {
        // Create a mock multipart file
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "avatar.png",
                "image/png",
                new byte[]{1, 2, 3, 4}
        );

        // Create a mock AccountsDTO with an invalid birthday format
        String invalidBirthday = "01-01-1990"; // Invalid format
        AccountsDTO accountsDTO = new AccountsDTO();
        accountsDTO.setFullName("John Doe");
        accountsDTO.setPhoneNumber("1234567890");
        accountsDTO.setEmail("test@example.com");
        accountsDTO.setGender("Male");
        accountsDTO.setFile(mockMultipartFile);
        accountsDTO.setBirthday(new Date()); // Set a random date

        // Perform the POST request to the createAccount endpoint with the invalid birthday format
        mockMvc.perform(post("/api/accounts")
                        .contentType("multipart/form-data")
                        .param("fullName", accountsDTO.getFullName())
                        .param("phoneNumber", accountsDTO.getPhoneNumber())
                        .param("email", accountsDTO.getEmail())
                        .param("gender", accountsDTO.getGender())
                        .param("birthday", invalidBirthday) // Use the invalid birthday format
                        .content(mockMultipartFile.getBytes()))
                .andExpect(status().is4xxClientError()); // Expect a 400 Bad Request response

        // Verify that accountService.createAccount() is not called
        verify(accountService, never()).createAccount(any());
    }

    @Test
    public void testCreateAccountSuccess() throws Exception {
        // Create a mock multipart file
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "avatar.png",
                "image/png",
                new byte[]{1, 2, 3, 4}
        );

        // Create a mock AccountsDTO with a valid birthday format
        AccountsDTO accountsDTO = new AccountsDTO();
        accountsDTO.setFullName("John Doe");
        accountsDTO.setPhoneNumber("1234567890");
        accountsDTO.setEmail("test@example.com");
        accountsDTO.setGender("Male");
        accountsDTO.setFile(mockMultipartFile);
        accountsDTO.setBirthday(new Date());

        // Create a mock AccountResponseDto
        AccountResponseDto mockResponseDto = new AccountResponseDto();
        mockResponseDto.setImageUrl("https://example.com/uploaded/avatar.png");

        // Mock the behavior of accountService.createAccount() to return a mock AccountResponseDto
        when(accountService.createAccount(accountsDTO)).thenReturn(mockResponseDto);

        // Perform the POST request to the createAccount endpoint with the valid birthday format
        mockMvc.perform(post("/api/accounts")
                        .contentType("multipart/form-data")
                        .param("fullName", accountsDTO.getFullName())
                        .param("phoneNumber", accountsDTO.getPhoneNumber())
                        .param("email", accountsDTO.getEmail())
                        .param("gender", accountsDTO.getGender())
                        .param("birthday", new SimpleDateFormat("yyyy-MM-dd").format(accountsDTO.getBirthday())) // Use the invalid birthday format
                        .content(mockMultipartFile.getBytes()))
                .andExpect(status().isOk());// Expect a 200 OK response
//                .andExpect(jsonPath("$.imageUrl", is(mockResponseDto.getImageUrl())));
        // Verify that accountService.createAccount() is called once with the correct AccountsDTO parameter
        verify(accountService, times(1)).createAccount(any());
    }
}