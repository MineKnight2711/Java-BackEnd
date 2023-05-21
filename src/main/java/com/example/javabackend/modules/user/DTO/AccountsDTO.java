package com.example.javabackend.modules.user.DTO;

import java.util.Date;

public class AccountsDTO {

    private Long accountId;
    private String password;

    public AccountsDTO(Long accountID, String password, String fullName, String phoneNumber, String email, String gender, Date birthday, String address, Long accountTypeId) {
        this.accountId = accountID;
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

    private String fullName;
    private String phoneNumber;
    private String email;
    private String gender;
    private Date birthday;
    private String address;
    private Long accountTypeId;

    public Long getAccountId() { return accountId; };
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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



    // các getter và setter

}
