package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long hallId;
    private Long userId;
    private Long ulbId;

    private String name;
    private String email;
    private String mobile;

    private LocalDate date;
    private String timeSlot;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.APPLIED;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
