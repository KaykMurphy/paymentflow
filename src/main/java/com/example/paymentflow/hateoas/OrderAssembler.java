package com.example.paymentflow.hateoas;

import com.example.paymentflow.controller.OrderController;
import com.example.paymentflow.dtos.OrderReceiptDTO;
import com.example.paymentflow.entity.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class OrderAssembler implements RepresentationModelAssembler<Order, EntityModel<OrderReceiptDTO>> {

    @Override
    public EntityModel<OrderReceiptDTO> toModel(Order order) {
        OrderReceiptDTO dto = new OrderReceiptDTO(
                order.getId(),
                order.getMoment(),
                order.getTotal(),
                order.getUser().getBalance()
        );

        return EntityModel.of(dto,
                linkTo(methodOn(OrderController.class).getById(order.getId())).withSelfRel()
        );
    }
}