package com.example.javabackend.modules.dishes.DTO;

import com.example.javabackend.entity.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class DishDto {
    @JsonProperty("dishName")
    public String dishName;
    @JsonProperty("image")
    public String image;
    @JsonProperty("price")
    public double price;
    @JsonProperty("sizeId")
    public Long sizeId;
    @JsonProperty("categoryId")
    public Long categoryId;
}