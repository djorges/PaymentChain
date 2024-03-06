package com.paymentchain.mscustomer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private String phone;

    private String iban;

    private String surname;

    private String address;

    @JsonIgnoreProperties("customer")
    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "customer",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ProductEntity> products;

    @Transient
    private List<?> transactions;

}
