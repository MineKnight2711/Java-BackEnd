package com.example.javabackend.modules.topping.controller;

import com.example.javabackend.entity.Topping;
import com.example.javabackend.modules.topping.Dto.ToppingDto;
import com.example.javabackend.modules.topping.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topping")
public class ToppingController {
    @Autowired
    ToppingService toppingService;

    @GetMapping()
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

    @PutMapping("/{id}")
    public Topping update(@PathVariable Long id, @RequestBody ToppingDto dto) {
        return this.toppingService.update(id,dto);
    }
}
