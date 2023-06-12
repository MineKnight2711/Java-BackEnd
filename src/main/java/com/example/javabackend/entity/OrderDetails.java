package com.example.javabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


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

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    @OneToMany(mappedBy = "orderDetails", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<OrderDetailsTopping> orderDetailsToppings;

}