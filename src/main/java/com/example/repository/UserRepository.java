package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Ensure this method exists and is used by AuthService
    Optional<User> findByUsername(String username);

    // (Optional) also keep findByEmail if your UI ever uses email login
    Optional<User> findByEmail(String email);
}
