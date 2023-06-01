package com.example.javabackend.modules.category.controller;

import com.example.javabackend.entity.Category;
import com.example.javabackend.modules.category.DTO.CategoryDTO;
import com.example.javabackend.modules.category.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import com.example.javabackend.modules.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryDTOList = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryDTOList);
    }

    //Get method
    //Get By ID Category
    @GetMapping("find/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Post method
    //Create Category
    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.addCategory(categoryDTO);
        return ResponseEntity.ok(category);
    }

    //Put method
    //Edit ID Category
    @PutMapping("edit/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,
                                                   @RequestBody CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryService.getCategoryById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setCategoryName(categoryDTO.getCategoryName());
            categoryService.updateCategory(category);
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Method
    // Delete Category
    @DeleteMapping("delete/{categoryId}")

    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        String message = "Deleted category with id: " + categoryId;
        return ResponseEntity.ok(message);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
