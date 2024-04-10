package com.example.javabackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Entity
@Data
@Getter
@Setter
@Table(name = "OrderDetails_Topping")
public class OrderDetailsTopping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_details_id")
    @JsonManagedReference
    private OrderDetails orderDetails;

    @ManyToOne
    @JoinColumn(name = "topping_id")
    @JsonManagedReference
    private Topping topping;
}