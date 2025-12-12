package com.example.paymentflow.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;

public record PurchaseRequestDTO(

        // Quem está comprando
        @NotNull(message = "userId é obrigatório")
        Long userId,

        // Qual produto será comprado
        @NotNull(message = "productId é obrigatório")
        Long productId,

        // Quantidade de itens
        @NotNull(message = "quantity é obrigatória")
        @Min(value = 1, message = "A quantidade mínima é 1")
        Integer quantity

) {}