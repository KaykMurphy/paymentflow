package com.example.paymentflow.dtos;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderReceiptDTO(
        Long orderId,
        Instant moment,
        BigDecimal total,
        BigDecimal newBalance
) {}
