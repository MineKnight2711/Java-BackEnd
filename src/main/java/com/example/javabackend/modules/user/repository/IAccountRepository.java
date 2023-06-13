package com.example.javabackend.modules.user.repository;

import com.example.javabackend.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Accounts, Long> {
    @Query("SELECT u FROM Accounts u where u.Email = ?1")
    Accounts findByEmail(String username);
    @Query("SELECT u.AccountID FROM Accounts u WHERE u.Email = ?1")
    Long getUserIdByEmail(String email);
    @Query("Select u from Accounts u where u.accountTypes.AccountTypeName = ?1")
    List<Accounts> findByAccountType(String accountType);

    @Query("SELECT at.AccountTypeName FROM AccountType at INNER JOIN at.accounts a WHERE a.AccountID = :userId")
    String[] getRolesOfUser(@Param("userId") Long userId);
}