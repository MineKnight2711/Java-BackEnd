package com.example.javabackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name="Size")
@Table(name="Size")
public class Size {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long SizeID;
    @Column(name = "SizeName",length = 50)
    private String SizeName;

    @OneToMany (mappedBy = "sizes", cascade = CascadeType.ALL)
    private List<Dishes> dishes;
}
