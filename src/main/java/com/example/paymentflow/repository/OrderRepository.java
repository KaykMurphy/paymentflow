package com.example.paymentflow.repository;

import com.example.paymentflow.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
