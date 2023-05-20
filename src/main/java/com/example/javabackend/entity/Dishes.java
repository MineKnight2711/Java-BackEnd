package com.example.javabackend.entity;
import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.DecimalMin;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Getter
@Setter
@Entity(name="Dishes")
@Table(name="Dishes")
public class Dishes {
    //    @Size(min = 10, max = 10)| Giới hạn kích thước
//    @Pattern(regexp = "^[a-zA-Z0-9]*$")| Regex chỉ nhân số và chữ
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long DishID;
    @Column(name = "DishName",length = 50)
    private String DishName;

    @Column(name = "Image")
    private String Image;
    //@PositiveOrZero | cách 1 dùng BeanValidation 2.0
    @DecimalMin(value = "0.0", inclusive = true)//Cách 2 dùng package javax.validation(phải cài đặt package này)
    @Column(name = "Price")
    private double Price;
    //Quan hệ 1 nhiều tới bản Size
    @ManyToOne
    @JoinColumn(name = "SizeID")
    private Size sizes;
    //Quan hệ 1 nhiều tới bản Category
    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category categories;
    @OneToMany (mappedBy = "dishes", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;
}
