package com.example.javabackend.admin.controller;

import com.example.javabackend.entity.Topping;
import com.example.javabackend.modules.topping.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ToppingWebController {
    @Autowired
    private ToppingService toppingService;
    @GetMapping("/suatopping/{topingId}")
    public String editTopping(@PathVariable("topingId") Long topingId, Model m){
        Topping topping=toppingService.getById(topingId);
        m.addAttribute("topping",topping);
        return "/topping/edit";
    }
}
