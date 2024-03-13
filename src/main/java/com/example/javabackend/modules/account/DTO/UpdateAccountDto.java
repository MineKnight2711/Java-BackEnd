package com.example.javabackend.modules.account.DTO;

import com.example.javabackend.utils.DatetimeDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class UpdateAccountDto {

    private Long accountId;
    private String fullName;
    private String phoneNumber;
    @NotNull
    @JsonProperty("birthday")
    @JsonDeserialize(using = DatetimeDeserialize.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String address;
    private String imageUrl;

    public UpdateAccountDto(Long accountID, String fullName, String phoneNumber, Date birthday, String address,String imageUrl) {
        this.accountId = accountID;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.address = address;
        this.imageUrl=imageUrl;
    }

    public UpdateAccountDto(){}


    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getAccountId() { return accountId; };

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





    // các getter và setter

}
