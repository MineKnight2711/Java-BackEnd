package com.example.javabackend.modules.order.Dto;

import com.example.javabackend.entity.Dishes;

import java.util.Date;
import java.util.List;

public class OrderDto {
    public Long accountId;
    public Date orderDate;

    public List<DishesDto> dishes;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<DishesDto> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishesDto> dishes) {
        this.dishes = dishes;
    }
}
