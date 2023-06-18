package com.example.javabackend.admin.controller;

import com.example.javabackend.entity.Category;
import com.example.javabackend.entity.Topping;
import com.example.javabackend.modules.category.repository.CategoryRepository;
import com.example.javabackend.modules.category.service.CategoryService;
import com.example.javabackend.modules.topping.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryWebController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String editCategory(Model m){
        List<Category> categories=categoryService.getAllCategories();
        m.addAttribute("categories",categories);
        return "/manage/category/list";
    }
    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Long id, Model m){
        Category category=categoryService.getCategoryById(id);
        m.addAttribute("category",category);
        return "/manage/category/edit";
    }
}
