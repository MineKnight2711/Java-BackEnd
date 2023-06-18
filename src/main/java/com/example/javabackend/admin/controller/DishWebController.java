package com.example.javabackend.admin.controller;

import com.example.javabackend.entity.Category;
import com.example.javabackend.entity.Dishes;
import com.example.javabackend.entity.Topping;
import com.example.javabackend.modules.category.service.CategoryService;
import com.example.javabackend.modules.dishes.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dish")
public class DishWebController {
    @Autowired
    private DishService dishService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String editTopping(Model m){
        List<Dishes> dishes=dishService.getAllDishes();
        m.addAttribute("dishes",dishes);
        return "/manage/product/list";
    }
    @GetMapping("/edit/{id}")
    public String editTopping(@PathVariable("id") Long id, Model m){
        System.out.println(id);
        Dishes dish=dishService.getDishById(id);
        List<Category> categories=categoryService.getAllCategories();
        m.addAttribute("dish",dish);
        m.addAttribute("categories",categories);
        return "manage/product/edit";
    }
}
