package com.example.javabackend.modules.order.Dto;

import com.example.javabackend.entity.Dishes;
import com.example.javabackend.entity.Topping;

import java.util.List;

public class DishesDto {
    public Long dishId;

    public int quantity;
    public List<ToppingDto> listTopping;

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public List<ToppingDto> getListTopping() {
        return listTopping;
    }

    public void setListTopping(List<ToppingDto> listTopping) {
        this.listTopping = listTopping;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
