package com.example.javabackend.modules.Products.service;

import com.example.javabackend.entity.Dishes;
import com.example.javabackend.modules.Products.DTO.ProductDTO;
import com.example.javabackend.modules.Products.repository.ProductRepository;
import com.example.javabackend.modules.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository dishesRepository;



    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAllDishes() {
        List<Dishes> dishes = dishesRepository.findAll();
        List<ProductDTO> dtos = new ArrayList<>();
        for (Dishes dish : dishes) {
            ProductDTO dto = new ProductDTO();
            dto.setDishID(dish.getDishID());
            dto.setDishName(dish.getDishName());
            dto.setImage(dish.getImage());
            dto.setPrice(dish.getPrice());
            dto.setSizeId(dish.getSizes().getSizeID());
            dto.setSizeName(dish.getSizes().getSizeName());
            dto.setCategoryId(dish.getCategories().getCategoryID());
            dto.setCategoryName(dish.getCategories().getCategoryName());
            dtos.add(dto);
        }
        return dtos;
    }

    public ProductDTO getDishById(Long id) {
        Optional<Dishes> optionalDish = dishesRepository.findById(id);
        if (optionalDish.isPresent()) {
            Dishes dish = optionalDish.get();
            ProductDTO dto = new ProductDTO();
            dto.setDishID(dish.getDishID());
            dto.setDishName(dish.getDishName());
            dto.setImage(dish.getImage());
            dto.setPrice(dish.getPrice());
            dto.setSizeId(dish.getSizes().getSizeID());
            dto.setSizeName(dish.getSizes().getSizeName());
            dto.setCategoryId(dish.getCategories().getCategoryID());
            dto.setCategoryName(dish.getCategories().getCategoryName());
            return dto;
        }
        return null;
    }

}