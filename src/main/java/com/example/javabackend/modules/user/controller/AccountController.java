package com.example.javabackend.modules.user.controller;

import com.example.javabackend.entity.Accounts;
import com.example.javabackend.modules.user.DTO.AccountsDTO;
import com.example.javabackend.modules.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountsService;

    @PostMapping
    public ResponseEntity<AccountsDTO> createAccount(@RequestBody AccountsDTO accountsDTO) {
        AccountsDTO createdAccountDTO = accountsService.createAccount(accountsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccountDTO);
    }

}