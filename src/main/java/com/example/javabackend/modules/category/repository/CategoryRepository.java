package com.example.javabackend.modules.category.repository;

import com.example.javabackend.entity.Accounts;
import com.example.javabackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    //Optional<Category> findById(Long id);

    @Query("SELECT u FROM Category u where u.CategoryID = ?1")
    Category find(Long categoryId);
}