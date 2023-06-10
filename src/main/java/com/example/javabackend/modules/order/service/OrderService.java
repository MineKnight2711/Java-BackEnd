package com.example.javabackend.modules.order.service;

import com.example.javabackend.entity.*;
import com.example.javabackend.modules.dishes.repository.IDishRepository;
import com.example.javabackend.modules.order.Dto.OrderDto;
import com.example.javabackend.modules.order.repository.OrderRepository;
import com.example.javabackend.modules.order_detail.repository.Order_DetailRepository;
import com.example.javabackend.modules.order_detail_topping.repository.OrderDetailToppingRepository;
import com.example.javabackend.modules.topping.repository.ToppingRepository;
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
    @Autowired
    private ToppingRepository toppingRepository;
    @Autowired
    private Order_DetailRepository orderDetailRepository;
    @Autowired
    private OrderDetailToppingRepository orderDetailToppingRepository;
    @Autowired
    private IDishRepository dishRepository;

    private void setOrder(Orders order, OrderDto dto) {
        order.setOrderDate(dto.orderDate);
        order.setAccounts(this.accountRepository.getById(dto.accountId));
        order.setAddress(this.accountRepository.getById(dto.accountId).getAddress());
    }
    public List<Orders> getAll() {
        return this.orderRepository.findAll();
    }
    public List<Orders> getByAccountId(Long userId) {
        return this.orderRepository.findByAccountId(userId);
    }
    public Orders getByOrderId(Long orderId) {
        return this.orderRepository.getById(orderId);
    }
    public Orders createOrder(OrderDto createOrderDto) {
        Orders order = new Orders();
        setOrder(order, createOrderDto);
        order.setStatus("Đang xử lý");
        order.setOrderDate(java.sql.Date.valueOf(LocalDate.now()));
        Orders response = this.orderRepository.save(order);
        for(int i = 0; i < createOrderDto.dishes.size(); i++) {
            System.out.println(createOrderDto.dishes.size());
            System.out.println(createOrderDto.dishes.get(i).dishId);
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setOrders(order);
            orderDetail.setQuantity(createOrderDto.dishes.get(i).quantity);
            Dishes dishes = dishRepository.findByDishId(createOrderDto.dishes.get(i).dishId);
            //System.out.println(dishes.getDishName());
            orderDetail.setDishes(dishes);
            orderDetailRepository.save(orderDetail);
            for (int j = 0; j < createOrderDto.dishes.get(i).listTopping.size(); j++) {
                OrderDetailsTopping detailsTopping = new OrderDetailsTopping();
                detailsTopping.setOrderDetails(orderDetail);
                Topping topping = toppingRepository.findByToppingId(createOrderDto.dishes.get(i).listTopping.get(j).toppingId);
                detailsTopping.setTopping(topping);
                orderDetailToppingRepository.save(detailsTopping);
            }
        }
        return response;
    }
    public Orders updateOrder(Long orderId) {
        Orders option = this.orderRepository.getById(orderId);
        option.setStatus("Đã xác nhận");
        return this.orderRepository.save(option);
    }
    public Orders cancelOrder(Long orderId) {
        Orders option = this.orderRepository.getById(orderId);
        option.setStatus("Cancel");
        return this.orderRepository.save(option);
    }
}