package com.example.javabackend.modules.dishes.service;

import com.example.javabackend.entity.Category;
import com.example.javabackend.entity.Dishes;
import com.example.javabackend.entity.Size;
import com.example.javabackend.modules.category.service.CategoryService;
import com.example.javabackend.modules.dishes.DTO.DishDto;
import com.example.javabackend.modules.dishes.repository.IDishRepository;
import com.example.javabackend.modules.category.repository.CategoryRepository;
import com.example.javabackend.modules.size.repository.SizeRepository;
import com.example.javabackend.modules.size.service.SizeService;
import com.example.javabackend.utils.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DishService {
    @Autowired
    private IDishRepository dishesRepository;
    @Autowired
    private UploadImageService uploadImageService;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired

    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

//    private void setDishDto(Dishes dish, DishDto dto) {
//        dto.setDishId(dish.getDishID());
//        dto.setPrice(dish.getPrice());
//        dto.setImage(dish.getImage());
//        dto.setDishName(dish.getDishName());
//        dto.setCategoryId(dish.getCategories().getCategoryID());
//        dto.setSizeId(dish.getSizes().getSizeID());
//        Category cate = categoryRepository.getById(dish.getCategories().getCategoryID());
//        //dto.setCategory(cate);
//    }

    public List<Dishes> getAllDishes() {
        return this.dishesRepository.findAll();
    }

    public Dishes getDishById(Long id) {
        Dishes response = dishesRepository.findByDishId(id);
        return response;
    }

    public List<Dishes> searchDish(String dishName) {
        List<Dishes> dishes = dishesRepository.findAll();
        List<Dishes> response = dishes.stream()
                .filter(dish -> dish.getDishName().contains(dishName))
                .collect(Collectors.toList());
        return response;
    }
    public List<Dishes> loadDishByCategory(Long categoryId) {
        List<Dishes> dishes = dishesRepository.findByCategoryId(categoryId);
        return dishes;
    }
    public Dishes createDish(MultipartFile image, DishDto newDish) throws IOException {
        Dishes dishes = new Dishes();
        dishes.setDishName(newDish.dishName);
        dishes.setPrice(newDish.price);
        Category cate = categoryRepository.find(newDish.categoryId);
        dishes.setCategories(cate);

        String imageUrl= uploadImageService.uploadImage(image,"dishesimage/", dishes.getDishName());
        System.out.println(imageUrl);
        dishes.setImage(imageUrl);
        return dishesRepository.save(dishes);
    }

//    public List<Dishes> getDishByCategory(Long id) {
//
//    }


    public Dishes updateDish(Dishes dishes) {
        return this.dishesRepository.save(dishes);
    }

}