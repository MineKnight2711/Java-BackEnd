package com.example.javabackend.modules.topping.repository;

import com.example.javabackend.entity.Dishes;
import com.example.javabackend.entity.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long> {
    @Query("SELECT u FROM Topping u where u.ToppingID = ?1")
    Topping findByToppingId(Long toppingId);
}
