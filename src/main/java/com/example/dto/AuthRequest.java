package com.example.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    // MUST be "username" and "password" to match backend login JSON
    private String username;
    private String password;
}
