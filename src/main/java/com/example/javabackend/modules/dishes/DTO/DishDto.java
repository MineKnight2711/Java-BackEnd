package com.example.javabackend.modules.dishes.DTO;

import com.example.javabackend.entity.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DishDto {
    private Long dishId;
    private String dishName;
    private String image;
    private double price;
    private Long sizeId;
    private String sizeName;
    private Long categoryId;
    private String categoryName;
    private Category category;
}