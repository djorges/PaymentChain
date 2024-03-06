package com.paymentchain.mstransactions.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private String ibanAccount;
    private LocalDateTime date;
    private double amount;
    private double fee;
    private String description;
    private String status;
    private String channel;
}