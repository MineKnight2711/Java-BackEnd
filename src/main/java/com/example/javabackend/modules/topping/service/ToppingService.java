package com.example.javabackend.modules.topping.service;

import com.example.javabackend.entity.Topping;
import com.example.javabackend.modules.topping.Dto.ToppingDto;
import com.example.javabackend.modules.topping.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToppingService {
    @Autowired
    ToppingRepository toppingRepository;

    private void setDto(ToppingDto dto, Topping result) {
        result.setToppingName(dto.toppingName);
        result.setUnit(dto.unit);
        result.setImage(dto.image);
        result.setPrice(dto.price);
    }

    public List<Topping> getAll() {
        return this.toppingRepository.findAll();
    }

    public Topping getById(Long id) {
        return this.toppingRepository.getById(id);
    }

    public Topping create(ToppingDto dto) {
        Topping topping = new Topping();
        setDto(dto, topping);
        return this.toppingRepository.save(topping);
    }

    public Topping update(Long id,ToppingDto dto) {
        Topping option = this.toppingRepository.getById(id);
        setDto(dto, option);
        return this.toppingRepository.save(option);
    }
}
