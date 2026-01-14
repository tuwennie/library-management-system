package com.tuwennie.library_management.controller;

import com.tuwennie.library_management.dto.RegisterRequest;
import com.tuwennie.library_management.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Kayıt olma ucu: http://localhost:8080/api/auth/register
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}