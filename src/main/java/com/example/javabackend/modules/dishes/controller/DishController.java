package com.example.javabackend.modules.dishes.controller;

import com.example.javabackend.entity.Dishes;
import com.example.javabackend.modules.dishes.DTO.DishDto;
import com.example.javabackend.modules.dishes.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/dish")
public class DishController {
    @Autowired
    private DishService dishesService;
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

    @PostMapping()
    public Dishes createDish(@RequestBody Dishes dishes) {
        return this.dishesService.createDish(dishes);
    }
    @PutMapping()
    public Dishes updateDish(@RequestBody Dishes dishes) {
        return this.dishesService.updateDish(dishes);
    }
}