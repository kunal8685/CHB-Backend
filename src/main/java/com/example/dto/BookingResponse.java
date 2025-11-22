package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Long id;
    private Long hallId;
    private String name;
    private String email;
    private String mobile;
    private String date;
    private String timeSlot;
    private String status;
}
