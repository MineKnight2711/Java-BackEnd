package com.example.javabackend.modules.user.repository;

import com.example.javabackend.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {
    @Query("SELECT u FROM Accounts u where u.Email = ?1")
    Accounts findByEmail(String email);

    @Query("Select u from Accounts u where u.accountTypes.AccountTypeName = ?1")
    Accounts findByAccountType(String accountType);
}