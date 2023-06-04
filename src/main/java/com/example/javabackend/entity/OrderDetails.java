package com.example.javabackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@Getter
@Setter
@Entity(name="OrderDetails")
@Table(name="OrderDetails")
public class OrderDetails {
    @Id
    @ManyToOne
    @JoinColumn(name = "DishID", nullable = false)
    private Dishes dishes;

    @Id
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "OrderID", nullable = false)
    private Orders orders;

    @Column(name = "ToppingName",length = 50)
    private String ToppingName;

    @Column(name = "Address",length = 255)
    private String Address;

    @Column(name = "Status",length =30)
    private String Status;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DeliveredDate")
    private Date DeliveredDate;
}