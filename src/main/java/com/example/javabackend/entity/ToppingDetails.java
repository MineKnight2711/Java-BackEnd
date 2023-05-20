package com.example.javabackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity(name="ToppingDetails")
@Table(name="ToppingDetails")
public class ToppingDetails {
    @Id
    @ManyToOne
    @JoinColumn(name = "DishID", nullable = false)
    private Dishes dishes;
    @Id
    @ManyToOne
    @JoinColumn(name = "ToppingID", nullable = true)
    private Topping toppings;
    @Id
    @ManyToOne
    @JoinColumn(name = "OrderID", nullable = false)
    private Orders orders;

}
