package com.example.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AuthResponse {
    private String token;
    private String role;
    private Long userId;
    private String username;
}
