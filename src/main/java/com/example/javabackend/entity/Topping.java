package com.example.javabackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@Entity(name="Topping")
@Table(name="Topping")
public class Topping {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long ToppingID;
    @Column(name = "ToppingName",length = 50)
    private String ToppingName;
    @Column(name = "Unit",length = 30)
    private String Unit;
    @DecimalMin(value = "0.0", inclusive = true)
    private double Price;
    @ManyToMany (mappedBy = "toppings")
    private List<OrderDetails> users = new ArrayList<>();

}
