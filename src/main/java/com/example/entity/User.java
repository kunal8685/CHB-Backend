package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

//src/main/java/com/example/entity/User.java
@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(unique=true, nullable=false)
 private String username;

 private String password;
 private String name;
 private String email;
 private String mobile;

 @Enumerated(EnumType.STRING)
 private Role role = Role.ROLE_USER;  // Default role is USER
}
