package com.example.javabackend.modules.order_detail.controller;

import com.example.javabackend.entity.OrderDetails;
import com.example.javabackend.entity.Orders;
import com.example.javabackend.modules.order_detail.service.Order_DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orderDetail")
public class Order_DetailController {
    @Autowired
    private Order_DetailService orderDetailService;

    @GetMapping("/{id}")
    public List<OrderDetails> getByOrderId(@PathVariable Long orderId) {
        return this.orderDetailService.getByOrderId(orderId);
    }
}
