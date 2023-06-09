package com.example.javabackend.modules.dishes.controller;

import com.example.javabackend.entity.Dishes;
import com.example.javabackend.modules.category.service.CategoryService;
import com.example.javabackend.modules.dishes.DTO.DishDto;
import com.example.javabackend.modules.dishes.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/dish")
public class DishController {
    @Autowired
    private DishService dishesService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public List<Dishes> getAllDishes() {
        return dishesService.getAllDishes();
    }
    @GetMapping("/{id}")
    public Optional<Dishes> getDishById(@PathVariable Long id) {
        return dishesService.getDishById(id);
    }
    @GetMapping("/search")
    public List<Dishes> search(@Param("name") String name) {
        return (dishesService.searchDish(name));
    }
    @PostMapping("/add")
    public Dishes createDish(@RequestParam MultipartFile file,
                             @RequestParam("dishName") String dishName,
                             @RequestParam("price") Double price,
                             @RequestParam("sizeId") Long sizeId,
                             @RequestParam("categoryId") Long categoryId)throws IOException{
        System.out.println("Run api Add");
        DishDto dto = new DishDto();
        dto.dishName = dishName;
        dto.price = price;
        dto.sizeId = sizeId;
        dto.categoryId = categoryId;
        return this.dishesService.createDish(file,dto);
    }


    @PutMapping()
    public Dishes updateDish(@RequestBody Dishes dishes) {
        return this.dishesService.updateDish(dishes);
    }
}