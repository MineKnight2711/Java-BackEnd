package com.example.javabackend.modules.Products.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private Long DishID;
    private String DishName;
    private String Image;
    private double Price;
    private Long sizeId;
    private String sizeName;
    private Long categoryId;
    private String categoryName;
}