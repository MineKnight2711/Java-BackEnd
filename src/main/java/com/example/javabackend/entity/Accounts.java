package com.example.javabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@Entity(name="Accounts")
@Table(name="Accounts")
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long AccountID;

    @Column(name = "Password",length = 500)
    private String Password;
    @Column(name = "FullName",length = 70)
    private String FullName;
    @Column(name = "PhoneNumber",length = 20)
    private String PhoneNumber;
    @Column(name = "Email",length = 50)
    private String Email;
    @Column(name = "Gender",length = 20)
    private String Gender;
    @Column(name = "ImageUrl",length = 1000)
    private String ImageUrl;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past(message = "NgaySinh phai nho hon ngay hien tai")
    @Column(name = "Brithday")
    private Date Brithday;

    @Column(name = "Address",length = 255)
    private String Address;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "AccountTypeID")
    private AccountType accountTypes;

    @OneToMany (mappedBy = "accounts", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Orders> orders;

}