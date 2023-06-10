package com.example.javabackend.modules.order_detail.repository;

import com.example.javabackend.entity.OrderDetails;
import com.example.javabackend.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order_DetailRepository extends JpaRepository<OrderDetails, Long> {
    @Query("SELECT u FROM OrderDetails u where u.orders.OrderID = ?1")
    List<OrderDetails> findByOrderId(Long orderId);
}
