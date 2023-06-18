package com.example.javabackend.modules.category.service;

import com.example.javabackend.entity.Category;
import com.example.javabackend.entity.Dishes;
import com.example.javabackend.modules.category.DTO.CategoryDTO;
import com.example.javabackend.modules.dishes.DTO.DishDto;
import com.example.javabackend.utils.UploadImageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.javabackend.modules.category.repository.ICategoryRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private UploadImageService uploadImageService;
    private void setDto(CategoryDTO dto, Category result) {
        result.setCategoryName(dto.categoryName);
        result.setImage(dto.image);
    }
    //Ham get list category
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }
    // Ham add category
    public Category addCategory(MultipartFile image, String categoryName) throws IOException {
        Category category = new Category();
        category.setCategoryName(categoryName);
        String imageUrl= uploadImageService.uploadImage(image,"categoriesimage/",categoryName);
        System.out.println(imageUrl);
        category.setImage(imageUrl);
        return categoryRepository.save(category);
    }
    public Category getCategoryById(Long id){
        return categoryRepository.find(id);
    }
    //Ham update lai category
    public Category updateCategory(Long id,MultipartFile file, CategoryDTO dto) throws IOException {
        Category category = this.categoryRepository.getById(id);
        uploadImageService.deleteExistImage("categoriesimage/",category.getCategoryName());
        String imageUrl;
        if(file!=null){
            imageUrl=uploadImageService.uploadImage(file,"categoriesimage/",dto.getCategoryName());
        }
        else{
            imageUrl= dto.getImage();
        }
        setDto(dto,category);
        category.setImage(imageUrl);
        return this.categoryRepository.save(category);
    }
    public Map<String, Object> deleteCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        Map<String, Object> response = new HashMap<>();
        if (!category.isPresent()) {
            response.put("success", false);
            response.put("message", "Không tìm thấy danh mục với mã " + categoryId);
            return response;
        }
        categoryRepository.delete(category.get());
        response.put("success", true);
        response.put("message", "Xoá danh mục thành công");
        return response;
    }
}