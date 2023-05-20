package com.example.javabackend.modules.user.controller;

import com.example.javabackend.modules.user.DTO.AccountTypeDTO;
import com.example.javabackend.modules.user.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<AccountTypeDTO>> getAllAccountTypes() {
        List<AccountTypeDTO> accountTypeDTOs = accountTypeService.getAllAccountTypes();
        return ResponseEntity.ok(accountTypeDTOs);
    }
    @PostMapping
    public ResponseEntity<AccountTypeDTO> createAccountType(@RequestBody AccountTypeDTO accountTypeDTO) {
        AccountTypeDTO createdAccountTypeDTO = accountTypeService.createAccountType(accountTypeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccountTypeDTO);
    }
}