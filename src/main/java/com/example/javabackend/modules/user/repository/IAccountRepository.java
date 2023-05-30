package com.example.javabackend.modules.user.repository;

import com.example.javabackend.entity.AccountType;
import com.example.javabackend.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<AccountType, Long> {

}
