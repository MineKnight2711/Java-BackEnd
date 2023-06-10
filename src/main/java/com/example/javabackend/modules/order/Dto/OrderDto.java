package com.example.javabackend.modules.order.Dto;

import com.example.javabackend.entity.Dishes;

import java.util.Date;
import java.util.List;

public class OrderDto {
    public Long accountId;
    public Date orderDate;

    public List<DishesDto> dishes;
}
