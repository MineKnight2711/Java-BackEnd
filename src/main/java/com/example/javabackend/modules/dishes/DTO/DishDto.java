package com.example.javabackend.modules.dishes.DTO;

import com.example.javabackend.entity.Category;
import lombok.Data;

@Data
public class DishDto {
    private Long DishID;
    private String DishName;
    private String Image;
    private double Price;
    private Long sizeId;
    private String sizeName;
    private Long categoryId;
    private String categoryName;
    private Category category;
}