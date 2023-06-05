package com.example.javabackend.modules.topping.repository;

import com.example.javabackend.entity.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToppingRepository extends JpaRepository<Topping, Long> {
}
