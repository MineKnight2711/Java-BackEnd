package com.example.javabackend.modules.Products.controller;

import com.example.javabackend.modules.Products.DTO.ProductDTO;
import com.example.javabackend.modules.Products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService dishesService;

    @GetMapping("/list")
    public List<ProductDTO> getAllDishes() {
        return dishesService.getAllDishes();
    }

    @GetMapping("/{id}")
    public ProductDTO getDishById(@PathVariable Long id) {
        return dishesService.getDishById(id);
    }

}