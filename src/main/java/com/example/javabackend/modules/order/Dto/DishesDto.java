package com.example.javabackend.modules.order.Dto;

import com.example.javabackend.entity.Dishes;
import com.example.javabackend.entity.Topping;

import java.util.List;

public class DishesDto {
    public Long dishId;
    public List<Topping> listTopping;

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public List<Topping> getListTopping() {
        return listTopping;
    }

    public void setListTopping(List<Topping> listTopping) {
        this.listTopping = listTopping;
    }
}
