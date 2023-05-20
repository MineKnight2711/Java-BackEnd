package com.example.javabackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Entity(name="AccountType")
@Table(name="AccountType")
public class AccountType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long AccountTypeID;
    @Column(name = "AccountTypeName",length = 50)
    private String AccountTypeName;

    @OneToMany (mappedBy = "accountTypes", cascade = CascadeType.ALL)
    private List<Accounts> accounts;
}
