package com.example.controller;

import com.example.dto.AuthRequest;
import com.example.dto.AuthResponse;
import com.example.entity.User;
import com.example.service.AuthService;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    // User registration
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User u) {
        if (u.getEmail() == null || u.getPassword() == null) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }
        if (userRepository.findByEmail(u.getEmail()).isPresent()) {
            return ResponseEntity.status(409).body("Email already exists");
        }
        User saved = authService.register(u);
        saved.setPassword(null); // Don't return password in response
        return ResponseEntity.ok(saved);
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
            AuthResponse resp = authService.login(req);
            return ResponseEntity.ok(resp);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(401).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Login failed");
        }
    }
}
