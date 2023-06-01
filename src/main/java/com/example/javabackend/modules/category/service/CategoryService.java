package com.example.javabackend.modules.category.service;

import com.example.javabackend.entity.Category;
import com.example.javabackend.modules.category.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    public List<Category> getAll() {
        List<Category> categories = this.categoryRepository.findAll().stream().toList();
        return categories;
    }

    public Category create(Category category) {
        Category response = this.categoryRepository.save(category);
        return response;
    }

    public Category findById(Long id) {
        Category response = this.categoryRepository.getById(id);
        return response;
    }

    public Category editById(Category category) {
        Category option = this.categoryRepository.getById(category.getCategoryID());
        option.setCategoryName(category.getCategoryName());
        return this.categoryRepository.save(option);
    }
}
