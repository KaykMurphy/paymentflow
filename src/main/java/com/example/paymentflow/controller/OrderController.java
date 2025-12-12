package com.example.paymentflow.controller;
import com.example.paymentflow.dtos.OrderReceiptDTO;
import com.example.paymentflow.dtos.PurchaseRequestDTO;
import com.example.paymentflow.entity.Order;
import com.example.paymentflow.hateoas.OrderAssembler;
import com.example.paymentflow.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderAssembler orderAssembler;

    @PostMapping
    public ResponseEntity<EntityModel<OrderReceiptDTO>> createOrder(
            @Valid @RequestBody PurchaseRequestDTO request) {

        Order order = orderService.createOrderReturnEntity(request);
        EntityModel<OrderReceiptDTO> model = orderAssembler.toModel(order);

        return ResponseEntity.created(
                linkTo(methodOn(OrderController.class).getById(order.getId())).toUri()
        ).body(model);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<OrderReceiptDTO>> getById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return ResponseEntity.ok(orderAssembler.toModel(order));
    }
}