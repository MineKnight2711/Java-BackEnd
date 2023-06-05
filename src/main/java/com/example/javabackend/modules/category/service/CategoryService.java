package com.example.javabackend.modules.category.service;

import com.example.javabackend.entity.Category;
import com.example.javabackend.modules.category.DTO.CategoryDTO;
import com.example.javabackend.utils.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.javabackend.modules.category.repository.ICategoryRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private UploadImageService uploadImageService;

    //Ham get list category
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }
    // Ham add category
    public Category addCategory(MultipartFile image, String categoryName) throws IOException {
        Category category = new Category();
        category.setCategoryName(categoryName);
        String imageUrl= uploadImageService.uploadImage(image);
        System.out.println(imageUrl);
        category.setImage(imageUrl);
        return categoryRepository.save(category);
    }
    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }
    //Ham update lai category
    public Category updateCategory(Long id,String categoryName){
        Category option = this.categoryRepository.getById(id);
        option.setCategoryName(categoryName);
        return categoryRepository.save(option);
    }

}