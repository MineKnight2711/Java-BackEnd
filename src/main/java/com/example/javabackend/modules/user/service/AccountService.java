package com.example.javabackend.modules.user.service;

import com.example.javabackend.entity.AccountType;
import com.example.javabackend.entity.Accounts;
import com.example.javabackend.modules.user.DTO.AccountTypeDTO;
import com.example.javabackend.modules.user.DTO.AccountsDTO;
import com.example.javabackend.modules.user.DTO.UpdateAccountDto;
import com.example.javabackend.modules.user.DTO.UserLoginDto;
import com.example.javabackend.modules.user.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountsRepository;

    //Get All Account
    public List<AccountsDTO> getAllAccount() {
        List<Accounts> account = accountsRepository.findAll();
        List<AccountsDTO> accountDTOs = new ArrayList<>();

        for (Accounts accounts : account) {
            AccountsDTO accountDTO = new AccountsDTO();
            accountDTO.setPassword("");
            accountDTO.setAccountTypeId(accounts.getAccountTypes().getAccountTypeID());
            accountDTO.setBirthday(accounts.getBrithday());
            accountDTO.setAddress(accounts.getAddress());
            accountDTO.setGender(accounts.getGender());
            accountDTO.setFullName(accounts.getFullName());
            accountDTO.setEmail(accounts.getEmail());
            accountDTO.setPhoneNumber(accounts.getPhoneNumber());
            accountDTOs.add(accountDTO);
        }
        return accountDTOs;
    }

    //Get By Id
    public AccountsDTO getById(Long id) {
        Accounts accounts = accountsRepository.getById(id);
        AccountsDTO getAccountDTO = new AccountsDTO();
        return new AccountsDTO(
                accounts.getAccountID(),
                "",
                accounts.getFullName(),
                accounts.getPhoneNumber(),
                accounts.getEmail(),
                accounts.getGender(),
                accounts.getBrithday(),
                accounts.getAddress(),
                accounts.getAccountTypes().getAccountTypeID()
        );
    }

    public AccountsDTO  createAccount(AccountsDTO accountsDTO) {
        Accounts accounts = new Accounts();
        // set các giá trị cho đối tượng accounts từ accountsDTO
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(accountsDTO.getPassword(), salt);
        accounts.setPassword(hashedPassword);
        accounts.setFullName(accountsDTO.getFullName());
        accounts.setPhoneNumber(accountsDTO.getPhoneNumber());
        accounts.setEmail(accountsDTO.getEmail());
        accounts.setGender(accountsDTO.getGender());
        accounts.setBrithday(accountsDTO.getBirthday());
        accounts.setAddress(accountsDTO.getAddress());
        // set AccountType cho Accounts
        AccountType accountType = new AccountType();
        accountType.setAccountTypeID(accountsDTO.getAccountTypeId());
        accounts.setAccountTypes(accountType);

        accounts = accountsRepository.save(accounts);

        return new AccountsDTO(
                accounts.getAccountID(),
                "",
                accounts.getFullName(),
                accounts.getPhoneNumber(),
                accounts.getEmail(),
                accounts.getGender(),
                accounts.getBrithday(),
                accounts.getAddress(),
                accounts.getAccountTypes().getAccountTypeID()
        );
    }

    public UpdateAccountDto updateUser(UpdateAccountDto accountUpdate) throws Exception {
        Accounts acc = accountsRepository.getById(accountUpdate.getAccountId());
        if (acc == null) {
            throw new Exception("User not found with username: " + accountUpdate.getEmail());
        }
        acc.setFullName(accountUpdate.getFullName());
        acc.setEmail(accountUpdate.getEmail());
        acc.setPhoneNumber(accountUpdate.getPhoneNumber());
        acc.setAddress(accountUpdate.getAddress());
        acc.setGender(accountUpdate.getGender());
        acc.setBrithday(accountUpdate.getBirthday());
        accountsRepository.save(acc);
        return new UpdateAccountDto(
                acc.getAccountID(),
                acc.getFullName(),
                acc.getPhoneNumber(),
                acc.getEmail(),
                acc.getGender(),
                acc.getBrithday(),
                acc.getAddress(),
                acc.getAccountTypes().getAccountTypeID()
        );
    }

    public UserLoginDto login(UserLoginDto user) {
        Accounts account = accountsRepository.findByEmail(user.getUsername());
        if(account == null) {
            user.setStatus("User not exist");
            return user;
        }
        if(!BCrypt.checkpw(user.getPassword(),account.getPassword())) {
            user.setStatus("Failure");
            return user;
        }
        user.setStatus("Success");
        return user;
    }
}