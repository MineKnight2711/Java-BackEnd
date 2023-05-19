package com.example.javabackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import java.util.List;

@Data
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
    @OneToMany (mappedBy = "toppings", cascade = CascadeType.ALL)
    private List<ToppingDetails> toppingDetails;

}
