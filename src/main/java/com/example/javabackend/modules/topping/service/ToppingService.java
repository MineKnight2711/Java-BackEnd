package com.example.javabackend.modules.topping.service;

import com.example.javabackend.entity.Category;
import com.example.javabackend.entity.Topping;
import com.example.javabackend.modules.topping.Dto.ToppingDto;
import com.example.javabackend.modules.topping.repository.ToppingRepository;
import com.example.javabackend.utils.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Topping create(ToppingDto dto) throws IOException {
        Topping topping = new Topping();
        setDto(dto, topping);
        String imageUrl=uploadImageService.uploadImage(dto.file,"toppingsimage/",topping.getToppingName());
        topping.setImage(imageUrl);
        return this.toppingRepository.save(topping);
    }

    //Delete topping web
    public void deleteTopping(Long id) {
        toppingRepository.deleteById(id);
    }



    public Topping update(Long id,ToppingDto dto) throws IOException {
        Topping option = this.toppingRepository.getById(id);
        uploadImageService.deleteExistImage("toppingsimage/",option.getToppingName());
        setDto(dto, option);
        String imageUrl;
        if(dto.file!=null){
            imageUrl=uploadImageService.uploadImage(dto.file,"toppingsimage/",option.getToppingName());
        }
        else{
            imageUrl= option.getImage();
        }
        option.setImage(imageUrl);
        return this.toppingRepository.save(option);
    }
    public String delete(Long id){
        Topping option = this.toppingRepository.getById(id);
        this.toppingRepository.delete(option);
        return "Success";
    }
}
