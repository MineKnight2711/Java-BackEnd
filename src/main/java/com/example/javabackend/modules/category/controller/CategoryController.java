package com.example.javabackend.modules.category.controller;

import com.example.javabackend.entity.Category;
import com.example.javabackend.modules.category.DTO.CategoryDTO;
import com.example.javabackend.modules.category.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //Get method
    //Get List Category
    @GetMapping
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
    public Category addCategory(@RequestParam MultipartFile image, String categoryName) throws IOException {
        return this.categoryService.addCategory(image,categoryName);
    }

    //Put method
    //Edit ID Category
    @PutMapping("edit/{id}")
    public Category updateCategory(
            @PathVariable Long id,
            @Param("categoryName") String categoryName
            ) {
        return this.categoryService.updateCategory(id, categoryName);
    }

    // Delete Method
    // Delete Category
    @DeleteMapping("/{categoryId}")
    public Map<String, Object> deleteCategory(@PathVariable Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
