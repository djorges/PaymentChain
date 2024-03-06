package com.paymentchain.mscustomer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    @Transient
    private String productName;

    @JsonIgnoreProperties("products")
    @ManyToOne(
        fetch = FetchType.LAZY,
        targetEntity = CustomerEntity.class
    )
    @JoinColumn(name = "customerId", nullable = false)
    private CustomerEntity customer;
}