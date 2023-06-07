package com.example.javabackend.modules.size.repository;

import com.example.javabackend.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
    @Query("SELECT u FROM Size u where u.SizeID = ?1")
    Size find(Long id);
}
