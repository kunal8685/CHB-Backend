package com.example.dto;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BookingRequest {
    private Long hallId;
    private Long userId;
    private String name;
    private String email;
    private String mobile;
    private String date;
    private String timeSlot;
    private Double amount;
    private Long ulbId;
}
