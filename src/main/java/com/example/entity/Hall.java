package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "halls")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Hall {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Integer capacity;
    private Double fee;
    private Long ulbId; // multi-tenancy
    private String imageUrl; // gallery single image for now
}
