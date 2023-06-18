package com.example.javabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import java.util.List;

@Data
@Getter
@Setter
@Entity(name="Size")
@Table(name="Size")
public class Size {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long SizeID;
    @Column(name = "SizeName",length = 50)
    private String SizeName;
    //inclusive ở đây tưức là bao gồm cả trường hợp 0.0 và giá trị double truyền và phải lớn hơn HOẶC bằng 0.0
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "Price")
    private double Price;
    @OneToMany (mappedBy = "sizes", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderDetails> orderDetails;
}