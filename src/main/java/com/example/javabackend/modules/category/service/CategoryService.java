package com.example.javabackend.modules.category.service;

import com.example.javabackend.entity.Category;
import com.example.javabackend.modules.category.DTO.CategoryDTO;
import com.example.javabackend.modules.category.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    //Ham get list category
    public List<CategoryDTO> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(category -> {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    categoryDTO.setCategoryId(category.getCategoryID());
                    categoryDTO.setCategoryName(category.getCategoryName());
                    return categoryDTO;
                })
                .collect(Collectors.toList());
    }
//    public Category allListCategory(CategoryDTO categoryDTO) {
//        Category category = new Category();
//        category.setCategoryName(categoryDTO.getCategoryName());
//        return categoryRepository.save(category);
//    }
    // Ham add category
    public Category addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        return categoryRepository.save(category);
    }

    //Ham delete category
    public void deleteCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new EntityNotFoundException("Category with id " + categoryId + " not found.");
        }
        categoryRepository.deleteById(categoryId);
    }
    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }

    //Ham update lai category
    public void updateCategory(Category category){
        categoryRepository.save(category);
    }

}