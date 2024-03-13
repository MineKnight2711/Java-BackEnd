package com.example.javabackend.modules.account.service;


import com.example.javabackend.entity.AccountType;
import com.example.javabackend.modules.account.repository.IAccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return this.accountTypeRepository.findByAccountTypeId(id);
    }

    //Create New
    public AccountType createAccountType(AccountType accountType) {
        return this.accountTypeRepository.save(accountType);
    }
}