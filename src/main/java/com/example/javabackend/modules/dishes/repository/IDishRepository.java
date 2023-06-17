package com.example.javabackend.modules.dishes.repository;

import com.example.javabackend.entity.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDishRepository extends JpaRepository<Dishes, Long> {
    @Query("SELECT u FROM Dishes u where u.DishID = ?1")
    Dishes findByDishId(Long categoryId);
    @Query("SELECT d FROM Dishes d WHERE d.categories.CategoryID = ?1")
    List<Dishes> findByCategoryId(Long categoryId);
}