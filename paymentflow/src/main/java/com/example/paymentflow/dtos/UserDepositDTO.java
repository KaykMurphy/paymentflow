package com.example.paymentflow.dtos;

import java.math.BigDecimal;

public record UserDepositDTO(
        BigDecimal amount
) {
}
