package com.example.javabackend.modules.category.controller;

import com.example.javabackend.entity.Category;
import com.example.javabackend.modules.category.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //Get method
    //Get List Category
    @GetMapping("/list")
    public List<Category> getAllCategories() {
        return this.categoryService.getAllCategories();
    }

    //Get method
    //Get By ID Category
    @GetMapping("/find/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id) {
        return this.categoryService.getCategoryById(id);
    }

    //Post method
    //Create Category
    @PostMapping("/add")
    public Category addCategory(@Param("categoryName")String categoryName) {
        return this.categoryService.addCategory(categoryName);
    }

    //Put method
    //Edit ID Category
    @PutMapping("edit/{id}")
    public Category updateCategory(
            @PathVariable Long id,
            @Param("categoryName") String categoryName) {
        return this.categoryService.updateCategory(id, categoryName);
    }

    // Delete Method
    // Delete Category

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}