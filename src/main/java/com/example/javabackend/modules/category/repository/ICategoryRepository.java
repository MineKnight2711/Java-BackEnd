package com.example.javabackend.modules.category.repository;

import com.example.javabackend.entity.Category;
import com.example.javabackend.entity.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT u FROM Category u where u.CategoryID = ?1")
    Category find(Long categoryId);
}
