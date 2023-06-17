package com.example.javabackend.admin.controller;

import com.example.javabackend.entity.Topping;
import com.example.javabackend.modules.topping.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/topping")
public class ToppingWebController {
    @Autowired
    private ToppingService toppingService;
    @GetMapping
    public String editTopping(Model m){
        List<Topping> toppings=toppingService.getAll();
        m.addAttribute("toppings",toppings);
        return "/topping/list";
    }
    @GetMapping("/edit/{id}")
    public String editTopping(@PathVariable("id") Long id, Model m){
        Topping topping=toppingService.getById(id);
        m.addAttribute("topping",topping);
        return "/topping/edit";
    }
}
