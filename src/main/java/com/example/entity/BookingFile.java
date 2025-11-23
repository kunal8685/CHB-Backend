package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booking_files")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BookingFile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookingId;
    private String filename;
    private String url;
    private String docType; // ID_PROOF, ADDRESS_PROOF, etc.
}
