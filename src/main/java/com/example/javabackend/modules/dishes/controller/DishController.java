package com.example.javabackend.modules.dishes.controller;

import com.example.javabackend.entity.Category;
import com.example.javabackend.entity.Dishes;
import com.example.javabackend.modules.category.service.CategoryService;
import com.example.javabackend.modules.dishes.DTO.DishDto;
import com.example.javabackend.modules.dishes.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/dish")
public class DishController {
    @Autowired
    private DishService dishesService;

    @GetMapping
    public List<Dishes> getAllDishes() {
        return dishesService.getAllDishes();
    }
    @GetMapping("/{id}")
    public Dishes getDishById(@PathVariable Long id) {
        return dishesService.getDishById(id);
    }
    @GetMapping("getByCategory/{categoryId}")
    public ResponseEntity<List<Dishes>> loadDishByCategory(@PathVariable("categoryId") Long categoryId) {
        List<Dishes> dishes = dishesService.loadDishByCategory(categoryId);
        if (dishes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dishes);
        }
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/search")
    public List<Dishes> search(@Param("name") String name) {
        return (dishesService.searchDish(name));
    }

//    @GetMapping("/category/{id}")
//    public List<Dishes> getByCategory(@PathVariable Long id) {
//        return this.dishesService.getDishByCategory(id);
//    }

    @PostMapping("/add")
    public ModelAndView createDish(@ModelAttribute DishDto dto) throws IOException {
        Dishes createdDish = this.dishesService.createDish(dto.file, dto);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/themsanpham");
        mav.addObject("result", "success");
        return mav;
    }
    
    @PutMapping()
    public Dishes updateDish(@RequestBody Dishes dishes) {
        return this.dishesService.updateDish(dishes);
    }
}