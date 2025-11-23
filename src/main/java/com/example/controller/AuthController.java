package com.example.controller;

import com.example.dto.AuthRequest;
import com.example.dto.AuthResponse;
import com.example.entity.User;
import com.example.service.AuthService;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Simple auth controller that supports register and login.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User u) {
        if (u.getUsername() == null || u.getPassword() == null) {
            return ResponseEntity.badRequest().body("username and password required");
        }
        if (userRepository.findByUsername(u.getUsername()).isPresent()) {
            return ResponseEntity.status(409).body("Username already exists");
        }
        User saved = authService.register(u);
        // Do not return password in response
        saved.setPassword(null);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
            AuthResponse resp = authService.login(req);
            return ResponseEntity.ok(resp);
        } catch (RuntimeException ex) {
            // Return 401 for invalid credentials with message
            return ResponseEntity.status(401).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Login failed");
        }
    }
}
