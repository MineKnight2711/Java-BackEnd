package com.example.javabackend.modules.dishes.service;

import com.example.javabackend.entity.Category;
import com.example.javabackend.entity.Dishes;
import com.example.javabackend.modules.dishes.DTO.DishDto;
import com.example.javabackend.modules.dishes.repository.IDishRepository;
import com.example.javabackend.modules.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DishService {
    @Autowired
    private IDishRepository dishesRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private void setDishDto(Dishes dish, DishDto dto) {
        dto.setDishId(dish.getDishID());
        dto.setPrice(dish.getPrice());
        dto.setImage(dish.getImage());
        dto.setDishName(dish.getDishName());
        dto.setCategoryId(dish.getCategories().getCategoryID());
        dto.setCategoryName(dish.getCategories().getCategoryName());
        dto.setSizeId(dish.getSizes().getSizeID());
        dto.setSizeName(dish.getSizes().getSizeName());
        Category cate = categoryRepository.getById(dish.getCategories().getCategoryID());
        //dto.setCategory(cate);
    }

    public List<Dishes> getAllDishes() {
        return this.dishesRepository.findAll();
    }

    public Optional<Dishes> getDishById(Long id) {
        Optional<Dishes> response = dishesRepository.findById(id);
        return response;
    }

    public List<Dishes> searchDish(String dishName) {
        List<Dishes> dishes = dishesRepository.findAll();
        List<Dishes> response = dishes.stream()
                .filter(dish -> dish.getDishName().contains(dishName))
                .collect(Collectors.toList());
        return response;
    }

    public Dishes createDish(Dishes dishes) {
        return this.dishesRepository.save(dishes);
    }

    public Dishes updateDish(Dishes dishes) {
        return this.dishesRepository.save(dishes);
    }

}