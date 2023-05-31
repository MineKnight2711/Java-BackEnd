package com.example.javabackend.modules.user.controller;

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
    public ResponseEntity<List<AccountsDTO>> getAllAccount() {
        List<AccountsDTO> accountDTOs = accountsService.getAllAccount();
        return ResponseEntity.ok(accountDTOs);
    }

    //Get By Id
    @GetMapping("id/{id}")
    public ResponseEntity<AccountsDTO> getById(@PathVariable Long id) {
        AccountsDTO getById = accountsService.getById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(getById);
    }

    //Create Account

    @PostMapping
    public ResponseEntity<AccountsDTO> createAccount(@RequestBody AccountsDTO accountsDTO) {
        AccountsDTO createdAccountDTO = accountsService.createAccount(accountsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccountDTO);
    }

    //Login
    @PostMapping("/login")
    public AccountResponseDto login(@RequestBody UserLoginDto user) throws Exception {
        return this.accountsService.login(user);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateAccountDto> updateAccount(@RequestBody UpdateAccountDto accountsDTO) throws Exception {
        UpdateAccountDto updateAccountDto = accountsService.updateUser(accountsDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateAccountDto);
    }

    @PutMapping("/password")
    public ChangePassDto changePass(@RequestBody ChangePassDto user) {
        return this.accountsService.changePass(user);
    }


}