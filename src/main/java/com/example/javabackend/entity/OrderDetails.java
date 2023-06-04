package com.example.javabackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;


@Data
@Getter
@Setter
@Entity(name="OrderDetails")
@Table(name="OrderDetails")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long OrderDetailsID;

    @ManyToOne
    @JoinColumn(name = "DishID", nullable = false)
    private Dishes dishes;

    @ManyToOne
    @JoinColumn(name = "OrderID", nullable = false)
    private Orders orders;
    @OneToMany(mappedBy = "orderDetails")
    private List<OrderDetailsTopping> orderDetailsToppings;
}