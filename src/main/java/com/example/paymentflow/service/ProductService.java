package com.example.paymentflow.service;

import com.example.paymentflow.dtos.ProductCreateDTO;
import com.example.paymentflow.dtos.ProductResponseDTO;
import com.example.paymentflow.entity.Product;
import com.example.paymentflow.exceptions.InsufficientBalanceException;
import com.example.paymentflow.exceptions.InsufficientStockException;
import com.example.paymentflow.exceptions.ResourceNotFoundException;
import com.example.paymentflow.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDTO createProduct(ProductCreateDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());

        Product saved = productRepository.save(product);

        return new ProductResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getStock()
        );
    }

    public void validateAndDecreaseStock(Product product, Integer quantity) {
        if (product.getStock() < quantity) {
            throw new InsufficientStockException("Out of stock");
        }
        product.setStock(product.getStock() - quantity);
    }

    public ProductResponseDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(p -> new ProductResponseDTO(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getStock()
                )).toList();
    }
}