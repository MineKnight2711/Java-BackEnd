package com.example.javabackend.modules.topping.controller;

import com.example.javabackend.entity.Dishes;
import com.example.javabackend.entity.Topping;
import com.example.javabackend.modules.dishes.DTO.DishDto;
import com.example.javabackend.modules.topping.Dto.ToppingDto;
import com.example.javabackend.modules.topping.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/topping")
public class ToppingController {
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

    @PostMapping("/add")
    public ModelAndView createTopping(@ModelAttribute ToppingDto toppingDto) throws IOException {
        Topping createTopping = this.toppingService.create(toppingDto.file, toppingDto);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/themtopping");
        mav.addObject("result", "success");
        return mav;
    }
    @PostMapping()
    public Topping create(@ModelAttribute ToppingDto dto) {
        return this.toppingService.create(dto);
    }
    @PutMapping("/{id}")
    public Topping update(@PathVariable Long id, @RequestBody ToppingDto dto) {
        return this.toppingService.update(id,dto);
    }

    // Delete Topping Web
    @GetMapping("/delete/{id}")
    public ModelAndView deleteTopping(@PathVariable("id") Long id) throws IOException{
        toppingService.deleteTopping(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/qltopping");
        mav.addObject("result", "success");
        return mav;
    }

}