package com.example.javabackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "deliver")
@Data
@Getter
@Setter
public class Deliver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DeliverID;

    @Column(name = "DeliverName")
    private String deliverName;

    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Column(name = "WorkState")
    private Boolean workState;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "imageUrl")
    private String imageUrl;
    @OneToMany(mappedBy = "deliver")
    private List<Orders> orders;

}
