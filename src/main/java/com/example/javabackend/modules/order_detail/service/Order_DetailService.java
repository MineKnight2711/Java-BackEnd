package com.example.javabackend.modules.order_detail.service;

import com.example.javabackend.entity.OrderDetails;
import com.example.javabackend.modules.order_detail.repository.Order_DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Order_DetailService {
    @Autowired
    private Order_DetailRepository orderDetailRepository;

    public List<OrderDetails> getByOrderId(Long orderId) {
        return this.orderDetailRepository.findByOrderId(orderId);
    }
}
