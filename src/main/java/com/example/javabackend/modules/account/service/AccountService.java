package com.example.javabackend.modules.account.service;

import com.example.javabackend.entity.AccountType;
import com.example.javabackend.entity.Accounts;
import com.example.javabackend.modules.account.DTO.*;
import com.example.javabackend.modules.account.repository.IAccountRepository;
import com.example.javabackend.utils.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.io.IOException;
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

    @Autowired
    private UploadImageService uploadImageService;

    private void setResponseDto(Accounts acc, AccountResponseDto response) {
        response.setAccountId(acc.getAccountID());
        response.setAddress(acc.getAddress());
        response.setBirthday(acc.getBrithday());
        response.setImageUrl(acc.getImageUrl());
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
                accounts.getImageUrl(),
                accounts.getFullName(),
                accounts.getPhoneNumber(),
                accounts.getEmail(),
                accounts.getGender(),
                accounts.getBrithday(),
                accounts.getAddress(),
                accounts.getAccountTypes().getAccountTypeID()
        );
    }
    public AccountResponseDto createAccount(AccountsDTO accountsDTO) throws IOException {
        Accounts accounts = new Accounts();
        // set các giá trị cho đối tượng accounts từ accountsDTO
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(accountsDTO.getPassword(), salt);
        accounts.setPassword(hashedPassword);
        accounts.setFullName(accountsDTO.getFullName());
        accounts.setPhoneNumber(accountsDTO.getPhoneNumber());
        accounts.setEmail(accountsDTO.getEmail());
        accounts.setGender(accountsDTO.getGender());
        if(accountsDTO.file!=null){
            String image= uploadImageService.uploadImage(accountsDTO.file,"userimage/", accounts.getFullName());
            accounts.setImageUrl(image);
        }
        else{
            accounts.setImageUrl(accountsDTO.getImageUrl());
        }
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
        if (accountsDTO.getBirthday() == null) {
            accountResponseDto.setBirthday(null);
        }
        accountResponseDto.setStatus("Success");
        return accountResponseDto;
    }






    public Date parseBirthday(Date birthday) {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
    public AccountResponseDto updateUser(UpdateAccountDto accountUpdate) throws Exception {

        Accounts acc = accountsRepository.getById(accountUpdate.getAccountId());
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        if (acc == null) {
            accountResponseDto.setStatus("Không tìm thấy tài khoản");
            return accountResponseDto;
        }
        acc.setFullName(accountUpdate.getFullName());
        acc.setPhoneNumber(accountUpdate.getPhoneNumber());
        acc.setAddress(accountUpdate.getAddress());
        acc.setImageUrl(accountUpdate.getImageUrl());
        if(accountUpdate.getBirthday()!=null){
            acc.setBrithday(parseBirthday(accountUpdate.getBirthday()));
        }
        else{
            acc.setBrithday(null);
        }
        setResponseDto(acc,accountResponseDto);
        accountResponseDto.setStatus("Cập nhật thành công!");
        accountsRepository.save(acc);

        return accountResponseDto;
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