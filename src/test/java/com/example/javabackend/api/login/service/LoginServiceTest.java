package com.example.javabackend.api.login.service;

import com.example.javabackend.entity.Accounts;
import com.example.javabackend.modules.account.DTO.AccountResponseDto;
import com.example.javabackend.modules.account.DTO.UserLoginDto;
import com.example.javabackend.modules.account.service.AccountService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest
{
    @Autowired
    private AccountService accountService;
//    @MockBean
//    private IAccountRepository accountRepository;
    private Accounts testAccount;
    @Before
    public void setUp() {
        testAccount = new Accounts();
        testAccount.setPassword("password");
        testAccount.setFullName("John Doe");
        testAccount.setPhoneNumber("1234567890");
        testAccount.setEmail("huynhphuocdat2@gmail.com");
        testAccount.setGender("Male");
        testAccount.setImageUrl("https://example.com/avatar.png");
        testAccount.setBrithday(new Date());
        testAccount.setAddress("123 Main St");
    }
    @Test
    public void loginWithValidCredentials() {
        UserLoginDto loginDto = new UserLoginDto("huynhphuocdat2@gmail.com", "Aa@123456");
        AccountResponseDto response = accountService.login(loginDto);
        Assertions.assertThat(response.getStatus()).isEqualTo("Success");
    }
    @Test
    public void loginWithInvalidUsername() {
        UserLoginDto loginDto = new UserLoginDto("invaliduser@example.com", "Aa@12345");
        AccountResponseDto response = accountService.login(loginDto);
        Assertions.assertThat(response.getStatus()).isEqualTo("User not exits");
    }
    @Test
    public void loginWithInvalidPassword() {
        UserLoginDto loginDto = new UserLoginDto("huynhphuocdat2@gmail.com", "wrongpassword");
        AccountResponseDto response = accountService.login(loginDto);
        Assertions.assertThat(response.getStatus()).isEqualTo("Pass Wrong! Failure");
    }
}
