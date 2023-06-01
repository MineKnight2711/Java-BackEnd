package com.example.javabackend.modules.user.service;

import com.example.javabackend.entity.AccountType;
import com.example.javabackend.entity.Accounts;
import com.example.javabackend.modules.user.DTO.*;
import com.example.javabackend.modules.user.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class AccountService {
    @Autowired
    private IAccountRepository accountsRepository;

    private void setResponseDto(Accounts acc, AccountResponseDto response) {
        response.setAccountId(acc.getAccountID());
        response.setAddress(acc.getAddress());
        response.setBirthday(acc.getBrithday());
        response.setEmail(acc.getEmail());
        response.setGender(acc.getGender());
        response.setAccountTypeId(acc.getAccountTypes().getAccountTypeID());
        response.setFullName(acc.getFullName());
        response.setPhoneNumber(acc.getPhoneNumber());
    }

    //Get All Account
    public List<AccountsDTO> getAllAccount() {
        List<Accounts> account = accountsRepository.findAll();
        List<AccountsDTO> accountDTOs = new ArrayList<>();

        for (Accounts accounts : account) {
            AccountsDTO accountDTO = new AccountsDTO();
            accountDTO.setAccountId(accounts.getAccountID());
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

    public AccountResponseDto createAccount(AccountsDTO accountsDTO) {

        Accounts accounts = new Accounts();
        // set các giá trị cho đối tượng accounts từ accountsDTO
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(accountsDTO.getPassword(), salt);
        accounts.setPassword(hashedPassword);
        accounts.setFullName(accountsDTO.getFullName());
        accounts.setPhoneNumber(accountsDTO.getPhoneNumber());
        accounts.setEmail(accountsDTO.getEmail());
        accounts.setGender(accountsDTO.getGender());
        if(accountsDTO.getBirthday()!=null){
            accounts.setBrithday(parseBirthday(accountsDTO.getBirthday()));
        }
        else{
            accountsDTO.setBirthday(null);
        }
        accounts.setAddress(accountsDTO.getAddress());
        // set AccountType cho Accounts
        AccountType accountType = new AccountType();
        accountType.setAccountTypeID(accountsDTO.getAccountTypeId());
        accounts.setAccountTypes(accountType);

        accounts = accountsRepository.save(accounts);
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        setResponseDto(accounts,accountResponseDto);
        return accountResponseDto;
    }
    public Date parseBirthday(Date birthday) {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(timeZone);

        String formattedDate = dateFormat.format(birthday);
        Date parsedBirthday;
        try {
            parsedBirthday = dateFormat.parse(formattedDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return parsedBirthday;
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

    public AccountResponseDto login(UserLoginDto user) {
        Accounts account = accountsRepository.findByEmail(user.getUsername());
        AccountResponseDto acc = new AccountResponseDto();
        if(account == null) {
            acc.setStatus("User not exits");
            return acc;
        }
        if(!BCrypt.checkpw(user.getPassword(),account.getPassword())) {
            acc.setStatus("Pass Wrong! Failure");
            return acc;
        }
        setResponseDto(account,acc);
        acc.setStatus("Success");
        return acc;
    }

    public ChangePassDto changePass(ChangePassDto user) {
        Accounts account = accountsRepository.findByEmail((user.getEmail()));
        if(account == null) {
            user.setStatus("User not exist");
            return user;
        }
        if(!BCrypt.checkpw(user.getPassword(),account.getPassword())) {
            user.setStatus("Pass Wrong");
            return user;
        }
        account.setPassword(BCrypt.hashpw(user.getNewPass(), BCrypt.gensalt()));
        accountsRepository.save(account);
        user.setPassword("");
        user.setNewPass("");
        user.setStatus("Success");
        return user;
    }
}