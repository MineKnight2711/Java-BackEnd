package com.example.javabackend.modules.user.controller;

import com.example.javabackend.entity.Accounts;
import com.example.javabackend.modules.user.DTO.*;
import com.example.javabackend.modules.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountsService;

    //Get All User
    @GetMapping("/all")
    public List<AccountsDTO> getAllAccount() {
        return this.accountsService.getAllAccount();
    }
    //Get By Id
    @GetMapping("id/{id}")
    public AccountsDTO getById(@PathVariable Long id) {
        return this.accountsService.getById(id);
    }
    //Create Account
    @PostMapping
    public AccountResponseDto createAccount(@RequestBody AccountsDTO accountsDTO) {
        return this.accountsService.createAccount(accountsDTO);
    }
    //Login
    @PostMapping("/login")
    public AccountResponseDto login(@RequestBody UserLoginDto user) throws Exception {
        return this.accountsService.login(user);
    }

    @PutMapping("/update")
    public UpdateAccountDto updateAccount(@RequestBody UpdateAccountDto accountsDTO) throws Exception {
        return this.accountsService.updateUser(accountsDTO);
    }

    @PutMapping("/password")
    public ChangePassDto changePass(@RequestBody ChangePassDto user) {
        return this.accountsService.changePass(user);
    }


}