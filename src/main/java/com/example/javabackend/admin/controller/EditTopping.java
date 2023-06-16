package com.example.javabackend.admin.controller;

import com.example.javabackend.entity.Topping;
import com.example.javabackend.modules.topping.Dto.ToppingDto;
import com.example.javabackend.modules.topping.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/toppings")
public class EditTopping {
    @Autowired
    ToppingService toppingService;

    @GetMapping
    public List<Topping> getAll() {
        return this.toppingService.getAll();
    }

    @GetMapping("/{id}")
    public Topping getById(@PathVariable Long id) {
        return this.toppingService.getById(id);
    }

    @PostMapping()
    public Topping create(@ModelAttribute ToppingDto dto) {
        return this.toppingService.create(dto);
    }


    @PostMapping("/edit")
    public String updateTopping(@PathVariable Long id, ToppingDto dto) {
        toppingService.update(id, dto);


        return "redirect:/toppings";

//        return "redirect:/topping";
    }
    @GetMapping("/edit-topping")
    public ModelAndView editToppingForm(@PathVariable("id") Long id, Model model) {
        Topping topping = toppingService.getById(id);

        ModelAndView mav = new ModelAndView();
        model.addAttribute("toppings", topping);

        mav.setViewName("redirect:/edit-topping");
        return mav;
    }

}
