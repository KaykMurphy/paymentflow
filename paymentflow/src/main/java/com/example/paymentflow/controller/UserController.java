package com.example.paymentflow.controller;

import com.example.paymentflow.dtos.UserCreateDTO;
import com.example.paymentflow.dtos.UserDepositDTO;
import com.example.paymentflow.dtos.UserResponseDTO;
import com.example.paymentflow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserCreateDTO dto) {
        UserResponseDTO created = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/{id}/deposit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deposit(@PathVariable Long id, @RequestBody @Valid UserDepositDTO dto) {
        userService.deposit(id, dto.amount());
    }
}