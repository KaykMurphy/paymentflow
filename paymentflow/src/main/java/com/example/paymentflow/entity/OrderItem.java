package com.example.paymentflow.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tb_order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private BigDecimal price;//price of the product at the time of purchase

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;

}
