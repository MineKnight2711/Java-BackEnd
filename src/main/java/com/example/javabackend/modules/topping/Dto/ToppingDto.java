package com.example.javabackend.modules.topping.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public class ToppingDto {
    @JsonProperty("toppingName")
    public String toppingName;
    @JsonProperty("price")
    public double price;
    @JsonProperty("unit")
    public String unit;
    @JsonProperty("image")
    public String image;

    @JsonProperty("file")
    public MultipartFile file;

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
