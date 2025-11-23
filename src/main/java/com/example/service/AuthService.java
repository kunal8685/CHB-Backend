package com.example.service;

import com.example.dto.AuthRequest;
import com.example.dto.AuthResponse;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * Register user helper (keeps behavior simple).
     */
    public User register(User u) {
        // encode password before saving
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        if (u.getRole() == null) {
            u.setRole(com.example.entity.Role.ROLE_USER);
        }
        return userRepository.save(u);
    }

    /**
     * Login - returns AuthResponse or throws RuntimeException on failure.
     */
    public AuthResponse login(AuthRequest req) {
        if (req == null || req.getUsername() == null || req.getPassword() == null) {
            throw new RuntimeException("Invalid login request");
        }

        Optional<User> ou = userRepository.findByUsername(req.getUsername());
        if (ou.isEmpty()) {
            // helpful debug message (do NOT leak in production)
            throw new RuntimeException("Invalid credentials: user not found");
        }

        User user = ou.get();
        boolean matches = passwordEncoder.matches(req.getPassword(), user.getPassword());
        if (!matches) {
            throw new RuntimeException("Invalid credentials: bad password");
        }

        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());
        return new AuthResponse(token, user.getRole().name(), user.getId(), user.getUsername());
    }
}
