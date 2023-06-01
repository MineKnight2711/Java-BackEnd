package com.example.javabackend.modules.category.controller;

import com.example.javabackend.entity.Category;
import com.example.javabackend.modules.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getAll() {
        List<Category> response = categoryService.getAll().stream().toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/id/{id}")
    public Category getById(@PathVariable Long id) {
        return this.categoryService.findById(id);
    }

    @PutMapping("/id/{id}")
    public Category editCategory(@RequestBody Category category) {
        return this.categoryService.editById(category);
    }

    @PostMapping()
    public Category create(@RequestBody Category category) {
        return this.categoryService.create(category);
    }
}
