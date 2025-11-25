package com.example.paymentflow.service;

import com.example.paymentflow.dtos.UserCreateDTO;
import com.example.paymentflow.dtos.UserResponseDTO;
import com.example.paymentflow.entity.User;
import com.example.paymentflow.exceptions.InsufficientBalanceException;
import com.example.paymentflow.exceptions.ResourceNotFoundException;
import com.example.paymentflow.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDTO createUser(UserCreateDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setBalance(dto.balance() != null ? dto.balance() : BigDecimal.ZERO);

        User saved = userRepository.save(user);

        return new UserResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getBalance()
        );
    }

    public void validateAndDebit(User user, BigDecimal totalAmount) {
        if (user.getBalance().compareTo(totalAmount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        user.setBalance(user.getBalance().subtract(totalAmount));
    }

    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getBalance()
        );
    }

    @Transactional
    public void deposit(Long userId, BigDecimal amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setBalance(user.getBalance().add(amount));
        userRepository.save(user);
    }
}