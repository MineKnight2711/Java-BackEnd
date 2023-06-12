package com.example.javabackend.modules.topping.service;

import com.example.javabackend.entity.Topping;
import com.example.javabackend.modules.topping.Dto.ToppingDto;
import com.example.javabackend.modules.topping.repository.ToppingRepository;
import com.example.javabackend.utils.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ToppingService {
    @Autowired
    ToppingRepository toppingRepository;
    @Autowired
    private UploadImageService uploadImageService;
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
        return this.toppingRepository.findByToppingId(id);
    }

    public Topping create(MultipartFile image, ToppingDto newTopping) throws IOException {
        Topping topping = new Topping();
        topping.setToppingName(newTopping.toppingName);
        setDto(newTopping, topping);
        String imageUrl= uploadImageService.uploadImage(image,"toppingsimage/", topping.getToppingName());
        System.out.println(imageUrl);
        topping.setImage(imageUrl);
        return this.toppingRepository.save(topping);
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
