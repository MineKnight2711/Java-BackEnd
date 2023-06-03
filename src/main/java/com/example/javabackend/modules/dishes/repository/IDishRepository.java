package com.example.javabackend.modules.dishes.repository;

import com.example.javabackend.entity.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDishRepository extends JpaRepository<Dishes, Long> {
}