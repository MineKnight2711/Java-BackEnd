package com.example.javabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@Entity(name="Orders")
@Table(name="Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long OrderID;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")

    @Column(name = "OrderDate")
    private Date OrderDate;

    @Column(name = "Address",length = 255)
    private String Address;

    @Column(name = "Status",length =30)
    private String Status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "AccountID")
    private Accounts accounts;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderDetails> orderDetails;

    @ManyToOne
    @JoinColumn(name = "DeliverID", referencedColumnName = "DeliverID")
    private Deliver deliver;

}