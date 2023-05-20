package com.example.javabackend.modules.user.service;


import com.example.javabackend.entity.AccountType;
import com.example.javabackend.modules.user.DTO.AccountTypeDTO;
import com.example.javabackend.modules.user.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountTypeService {
    @Autowired
    private IAccountRepository accountTypeRepository;

    public List<AccountTypeDTO> getAllAccountTypes() {
        List<AccountType> accountTypes = accountTypeRepository.findAll();
        List<AccountTypeDTO> accountTypeDTOs = new ArrayList<>();

        for (AccountType accountType : accountTypes) {
            AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
            accountTypeDTO.setId(accountType.getAccountTypeID());
            accountTypeDTO.setName(accountType.getAccountTypeName());
            accountTypeDTOs.add(accountTypeDTO);
        }

        return accountTypeDTOs;
    }
    public AccountTypeDTO createAccountType(AccountTypeDTO accountTypeDTO) {
        AccountType accountType = new AccountType();
        accountType.setAccountTypeName(accountTypeDTO.getName());
        accountType = accountTypeRepository.save(accountType);
        AccountTypeDTO createdAccountTypeDTO = new AccountTypeDTO();
        createdAccountTypeDTO.setId(accountType.getAccountTypeID());
        createdAccountTypeDTO.setName(accountType.getAccountTypeName());
        return createdAccountTypeDTO;
    }

}