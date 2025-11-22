package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booking_files")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private String fileName;

    private String originalName;

    private String fileType;

    private Long fileSize;
}
