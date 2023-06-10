package com.example.javabackend.modules.order_detail_topping.repository;

import com.example.javabackend.entity.OrderDetailsTopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailToppingRepository extends JpaRepository<OrderDetailsTopping, Long> {
}
