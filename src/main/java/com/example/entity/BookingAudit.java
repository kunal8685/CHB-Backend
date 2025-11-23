package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking_audit")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BookingAudit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;
    private Long userId;
    private String action; // APPROVED, REJECTED, PAID, etc.
    private String comment;
    private LocalDateTime whenAction = LocalDateTime.now();
}
