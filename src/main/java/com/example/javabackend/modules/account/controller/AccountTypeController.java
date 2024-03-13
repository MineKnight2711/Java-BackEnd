package com.example.javabackend.modules.account.controller;

import com.example.javabackend.entity.AccountType;
import com.example.javabackend.modules.account.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account-types")
public class AccountTypeController {
    @Autowired
    private AccountTypeService accountTypeService;

    @GetMapping
    public List<AccountType> getAllAccountTypes() {
        return accountTypeService.getAllAccountTypes();
    }

    @PostMapping
    public AccountType createAccountType(@ModelAttribute AccountType accountType) {
        return accountTypeService.createAccountType(accountType);
    }

    @GetMapping("id/{id}")
    public AccountType getById(@PathVariable Long id) {
        return accountTypeService.getById(id);
    }

}