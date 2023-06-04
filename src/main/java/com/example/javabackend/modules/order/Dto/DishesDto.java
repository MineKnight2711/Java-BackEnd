package com.example.javabackend.modules.order.Dto;

import com.example.javabackend.entity.Dishes;
import com.example.javabackend.entity.Topping;

import java.util.List;

public class DishesDto {
    public Dishes dishes;
    public List<Topping> listTopping;

    public Dishes getDishes() {
        return dishes;
    }

    public void setDishes(Dishes dishes) {
        this.dishes = dishes;
    }

    public List<Topping> getListTopping() {
        return listTopping;
    }

    public void setListTopping(List<Topping> listTopping) {
        this.listTopping = listTopping;
    }
}
