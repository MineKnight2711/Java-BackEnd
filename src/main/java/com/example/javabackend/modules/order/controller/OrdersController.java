package com.example.javabackend.modules.order.controller;

import java.util.List;

import com.example.javabackend.entity.Orders;
import com.example.javabackend.modules.order.Dto.OrderDto;
import com.example.javabackend.modules.order.service.OrderService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public List<Orders> getAll() {
        return this.orderService.getAll();
    }

    @PostMapping("")
    public Orders createOrder(@RequestBody OrderDto createOrderDto) {
        return orderService.createOrder(createOrderDto);
    }

    @GetMapping("/{orderId}")
    public Orders getOrderById(@PathVariable Long orderId) throws Exception {
        return orderService.getByOrderId(orderId);
    }

    @GetMapping("/{accountId}")
    public List<Orders> getOrdersByAccountId(@PathVariable Long accountId) {
        return orderService.getByAccountId(accountId);
    }

    @PutMapping("/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        var option = orderService.cancelOrder(orderId);
        if(option != null) {
            return "Failure";
        }
        return "Success";
    }

    @PutMapping("/id/{id}")
    public String confirmOrder(@PathVariable Long orderId) {
        var option = this.orderService.updateOrder(orderId);
        if(option != null) {
            return "Failure";
        }
        return "Success";
    }
}