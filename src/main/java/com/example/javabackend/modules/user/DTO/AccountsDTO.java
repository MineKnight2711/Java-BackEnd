package com.example.javabackend.modules.user.DTO;

import com.example.javabackend.utils.DatetimeDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Date;

public class AccountsDTO {
    private Long accountId;
    @NotNull
    private String password;
    @NotNull
    private String imageUrl;
    @NotNull

    public MultipartFile file;
    @NotNull
    private String fullName;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;
    @NotNull
    private String gender;
    @NotNull
    @JsonProperty("birthday")
    @JsonDeserialize(using = DatetimeDeserialize.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @NotNull
    private String address;
    private Long accountTypeId;
    public AccountsDTO(Long accountID, String imageUrl, String password, String fullName, String phoneNumber, String email, String gender, Date birthday, String address, Long accountTypeId) {
        this.accountId = accountID;
        this.imageUrl=imageUrl;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.accountTypeId = accountTypeId;
    }

    public AccountsDTO(){}

    public Long getAccountId() { return accountId; };

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
    // các getter và setter

}
