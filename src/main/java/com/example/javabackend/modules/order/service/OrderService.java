package com.example.javabackend.modules.order.service;

import com.example.javabackend.entity.Accounts;
import com.example.javabackend.entity.OrderDetails;
import com.example.javabackend.entity.OrderDetailsTopping;
import com.example.javabackend.entity.Orders;
import com.example.javabackend.modules.order.Dto.OrderDto;
import com.example.javabackend.modules.order.repository.OrderRepository;
import com.example.javabackend.modules.user.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private IAccountRepository accountRepository;

    private void setOrder(Orders order, OrderDto dto) {
        order.setOrderDate(dto.orderDate);
        order.setAccounts(this.accountRepository.getById(dto.accountId));
    }

    public Orders createOrder(OrderDto createOrderDto) {
        Orders order = new Orders();
        setOrder(order, createOrderDto);
        order.setStatus("Đang xử lý");
        order.setOrderDate(java.sql.Date.valueOf(LocalDate.now()));
        Orders response = this.orderRepository.save(order);
        for(int i = 0; i < createOrderDto.dishes.size(); i++) {
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setOrders(order);
            for (int j = 0; j < createOrderDto.dishes.get(i).listTopping.size(); j++) {
                OrderDetailsTopping detailsTopping = new OrderDetailsTopping();
                detailsTopping.setOrderDetails(orderDetail);
                detailsTopping.setTopping(createOrderDto.dishes.get(i).listTopping.get(j));
            }
        }
        return response;
    }

//    public List<Orders> getByUserId(Long userId) {
//        return this.orderRepository.fin
//    }
}