package com.example.javabackend.modules.deliver.repository;

import com.example.javabackend.entity.Deliver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverRepository extends JpaRepository<Deliver,Long>{

}
