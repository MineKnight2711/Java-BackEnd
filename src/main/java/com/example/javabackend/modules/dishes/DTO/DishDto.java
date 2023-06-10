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


    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

}