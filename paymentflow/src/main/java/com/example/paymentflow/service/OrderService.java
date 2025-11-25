package com.example.paymentflow.service;

import com.example.paymentflow.dtos.PurchaseRequestDTO;
import com.example.paymentflow.entity.Order;
import com.example.paymentflow.entity.OrderItem;
import com.example.paymentflow.entity.Product;
import com.example.paymentflow.entity.User;
import com.example.paymentflow.enums.Status;
import com.example.paymentflow.exceptions.ResourceNotFoundException;
import com.example.paymentflow.repository.OrderItemRepository;
import com.example.paymentflow.repository.OrderRepository;
import com.example.paymentflow.repository.ProductRepository;
import com.example.paymentflow.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;
    private final ProductService productService;

    @Transactional
    public Order createOrderReturnEntity(PurchaseRequestDTO request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        productService.validateAndDecreaseStock(product, request.quantity());

        BigDecimal total = product.getPrice()
                .multiply(BigDecimal.valueOf(request.quantity()));

        userService.validateAndDebit(user, total);

        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(Status.PAID);
        order.setUser(user);
        order.setTotal(total);
        orderRepository.save(order);

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(request.quantity());
        item.setPrice(product.getPrice());
        orderItemRepository.save(item);

        return order;
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
}