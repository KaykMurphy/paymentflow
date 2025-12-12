package com.example.paymentflow.dtos;

import java.math.BigDecimal;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        BigDecimal balance
) {}