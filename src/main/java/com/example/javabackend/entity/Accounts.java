package com.example.javabackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@Entity(name="Accounts")
@Table(name="Accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long AccountID;

    @Column(name = "Password",length = 500)
    private String Password;
    @Column(name = "FullName",length = 70)
    private String FullName;
    @Column(name = "PhoneNumber",length = 20)
    private String PhoneNumber;
    @Column(name = "Email",length = 50)
    private String Email;
    @Column(name = "Gender",length = 20)
    private String Gender;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past(message = "NgaySinh phai nho hon ngay hien tai")
    @Column(name = "Brithday")
    private Date Brithday;

    @Column(name = "Address",length = 255)
    private String Address;

    @ManyToOne
    @JoinColumn(name = "AccountTypeID")
    private AccountType accountTypes;


    @OneToMany (mappedBy = "accounts", cascade = CascadeType.ALL)
    private List<Orders> orders;

    public Long getAccountID() {
        return AccountID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getBrithday() {
        return Brithday;
    }

    public void setBrithday(Date brithday) {
        Brithday = brithday;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public AccountType getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(AccountType accountTypes) {
        this.accountTypes = accountTypes;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}