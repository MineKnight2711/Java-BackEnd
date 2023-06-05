package com.example.javabackend.entity;


import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;

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
    private OrderDetails orderDetails;

    @ManyToOne
    @JoinColumn(name = "topping_id")
    private Topping topping;


}