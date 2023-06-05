package com.example.javabackend.modules.category.service;

import com.example.javabackend.entity.Category;
import com.example.javabackend.modules.category.DTO.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.javabackend.modules.category.repository.ICategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    //Ham get list category
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }
    // Ham add category
    public Category addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setImage(categoryDTO.getImage());
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