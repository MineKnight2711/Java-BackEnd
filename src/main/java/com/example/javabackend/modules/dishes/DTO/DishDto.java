package com.example.javabackend.modules.dishes.DTO;

import com.example.javabackend.entity.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class DishDto {
    public String dishName;
    public String image;
    public double price;
    public Long sizeId;
    public Long categoryId;
}