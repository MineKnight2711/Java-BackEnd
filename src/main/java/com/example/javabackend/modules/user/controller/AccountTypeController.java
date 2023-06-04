package com.example.javabackend.modules.user.controller;

import com.example.javabackend.entity.AccountType;
import com.example.javabackend.modules.user.DTO.AccountTypeDTO;
import com.example.javabackend.modules.user.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public AccountType createAccountType(@RequestBody AccountType accountType) {
        return accountTypeService.createAccountType(accountType);
    }

    @GetMapping("id/{id}")
    public AccountType getById(@PathVariable Long id) {
        return accountTypeService.getById(id);
    }

}