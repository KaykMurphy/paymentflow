package com.example.paymentflow.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record UserCreateDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password is required")
        String password,

        BigDecimal balance
) {}