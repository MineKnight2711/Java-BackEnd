package com.example.javabackend.admin.controller;

import com.example.javabackend.entity.AccountType;
import com.example.javabackend.entity.Topping;
import com.example.javabackend.modules.category.service.CategoryService;
import com.example.javabackend.modules.dishes.DTO.DishDto;
import com.example.javabackend.modules.size.service.SizeService;
import com.example.javabackend.modules.topping.service.ToppingService;
import com.example.javabackend.modules.user.DTO.AccountTypeDTO;
import com.example.javabackend.modules.user.DTO.AccountsDTO;
import com.example.javabackend.modules.user.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ManageToppingController {
    @Autowired
    ToppingService toppingService;

    @GetMapping("/qltopping")
    public String showAllTopping(Model model) {
        List<Topping> toppings = toppingService.getAll();
        model.addAttribute("toppings", toppings);
        model.addAttribute("toppingsTitle", "Danh sách các topping");
        return "manage/topping/index";
    }

    @GetMapping("/edit-topping")
    public String editTopping(Model model) {

        model.addAttribute("toppings");
        model.addAttribute("toppingsTitle", "Danh sách các topping");
        return "manage/topping/index1";
    }

}
