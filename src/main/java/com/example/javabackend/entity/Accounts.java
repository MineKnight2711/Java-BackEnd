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

    @Column(name = "Password",length = 50)
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
}