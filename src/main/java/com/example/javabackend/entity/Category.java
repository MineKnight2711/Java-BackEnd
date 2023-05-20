package com.example.javabackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Getter
@Setter
@Entity(name="Category")
@Table(name="Category")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long CategoryID;
    @Column(name = "CategoryName",length = 50)
    private String CategoryName;
    @OneToMany (mappedBy = "categories", cascade = CascadeType.ALL)
    private List<Dishes> dishes;
}
