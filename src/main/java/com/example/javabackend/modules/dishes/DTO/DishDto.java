package com.example.javabackend.modules.dishes.DTO;

import com.example.javabackend.entity.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
@Data
@Getter
@Setter
public class DishDto {
    @NotNull
    @JsonProperty("dishName")
    public String dishName;
    @NotNull
    @JsonProperty("image")
    public String image;
    @NotNull
    @JsonProperty("price")
    public double price;
    @NotNull
    @JsonProperty("sizeId")
    public Long sizeId;
    @NotNull
    @JsonProperty("categoryId")
    public Long categoryId;
}