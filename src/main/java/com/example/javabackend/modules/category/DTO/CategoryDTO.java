package com.example.javabackend.modules.category.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
    private String image;
}

