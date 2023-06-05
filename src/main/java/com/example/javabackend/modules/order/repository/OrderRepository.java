package com.example.javabackend.modules.order.repository;

import java.util.List;

import com.example.javabackend.entity.Accounts;
import com.example.javabackend.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query("SELECT u FROM Orders u where u.accounts.AccountID = ?1")
    List<Orders> findByAccountId(Long accountId);
}