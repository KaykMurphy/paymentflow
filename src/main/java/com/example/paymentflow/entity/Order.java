package com.example.paymentflow.entity;

import com.example.paymentflow.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant moment; // datas globais

    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status;

}
