package com.example.controller;



import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Booking;
import com.example.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final BookingService bookingService;

    @GetMapping("/bookings")
    public List<Booking> all() {
        return bookingService.listAll();
    }

    @PutMapping("/bookings/{id}/approve")
    public Booking approve(@PathVariable Long id) {
        return bookingService.approve(id);
    }

    @PutMapping("/bookings/{id}/reject")
    public Booking reject(@PathVariable Long id) {
        return bookingService.reject(id);
    }
}
