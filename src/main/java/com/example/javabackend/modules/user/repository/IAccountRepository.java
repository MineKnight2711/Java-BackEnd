package com.example.javabackend.modules.user.repository;

import com.example.javabackend.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Accounts, Long> {
    @Query("SELECT u FROM Accounts u where u.Email = ?1")
    Accounts findByEmail(String email);

    @Query("Select u from Accounts u where u.accountTypes.AccountTypeName = ?1")
    List<Accounts> findByAccountType(String accountType);

    @Query(value = "SELECT r.account_type_name FROM accounttype r INNER JOIN user_role ur ON r.accounttype_id = ur.role_id WHERE ur.user_id = ?1", nativeQuery = true)
    String[] getRolesOfUser(Long userId);
}