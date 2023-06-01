package com.example.javabackend.modules.Products.repository;

import com.example.javabackend.entity.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Dishes, Long> {
}