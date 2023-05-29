package com.example.javabackend.modules.user.service;

import com.example.javabackend.entity.AccountType;
import com.example.javabackend.entity.Accounts;
import com.example.javabackend.modules.user.DTO.AccountTypeDTO;
import com.example.javabackend.modules.user.DTO.AccountsDTO;
import com.example.javabackend.modules.user.DTO.UpdateAccountDto;
import com.example.javabackend.modules.user.repository.AccountRepository;
import com.example.javabackend.modules.user.repository.IAccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountsRepository;
    @Autowired
    private AccountTypeService accountTypeService ;
    //Get All Account
    public List<AccountsDTO> getAllAccount() {
        List<Accounts> accounts = accountsRepository.findAll();
        List<AccountsDTO> accountDTOs = new ArrayList<>();

        for (Accounts account : accounts) {
            AccountsDTO accountDTO = new AccountsDTO();
            accountDTO.setPassword("");
            accountDTO.setAccountTypeId(account.getAccountTypes().getAccountTypeID());

            if(account.getBrithday()!=null){
                accountDTO.setBirthday(parseBirthday(account.getBrithday()));
            }
            else{
                accountDTO.setBirthday(null);
            }
            accountDTO.setAddress(account.getAddress());
            accountDTO.setGender(account.getGender());
            accountDTO.setFullName(account.getFullName());
            accountDTO.setEmail(account.getEmail());
            accountDTO.setPhoneNumber(account.getPhoneNumber());
            accountDTO.setAccountId(account.getAccountID());
            accountDTO.setPassword(account.getPassword());
            accountDTOs.add(accountDTO);
        }
        return accountDTOs;
    }
    //Ép kiểu ngày tháng
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

    public Accounts createAccount(AccountsDTO accountsDTO) throws ParseException {
        Accounts accounts=new Accounts();
        // set các giá trị cho đối tượng accounts từ accountsDTO
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(accountsDTO.getPassword(), salt);
        AccountTypeDTO accountTypeDTO=accountTypeService.getById(accountsDTO.getAccountTypeId());
        AccountType accountType=new AccountType();
        accountType.setAccountTypeName(accountTypeDTO.getName());
        accountType.setAccountTypeID(accountTypeDTO.getId());

        accounts.setFullName(accountsDTO.getFullName());
        accounts.setAddress(accountsDTO.getAddress());
        accounts.setPhoneNumber(accountsDTO.getPhoneNumber());
        accounts.setGender(accountsDTO.getGender());
        accounts.setEmail(accountsDTO.getEmail());

        accounts.setBrithday(accountsDTO.getBirthday());
        accounts.setPassword(hashedPassword);
        accounts.setAccountTypes(accountType);
        accounts = accountsRepository.save(accounts);
        return accounts;
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
}