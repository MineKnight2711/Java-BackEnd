package com.example.javabackend.modules.user.service;


import com.example.javabackend.entity.AccountType;
import com.example.javabackend.modules.user.DTO.AccountTypeDTO;
import com.example.javabackend.modules.user.repository.IAccountRepository;
import com.example.javabackend.modules.user.repository.IAccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountTypeService {
    @Autowired
    private IAccountTypeRepository accountTypeRepository;

    //Get List
    public List<AccountType> getAllAccountTypes() {
        return this.accountTypeRepository.findAll();
    }

    // Get By Id
    public AccountType getById(Long id) {
        return this.accountTypeRepository.getById(id);
    }

    //Create New
    public AccountType createAccountType(AccountType accountType) {
        return this.accountTypeRepository.save(accountType);
    }
}