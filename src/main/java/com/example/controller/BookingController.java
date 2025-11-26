package com.example.controller;

import com.example.dto.BookingRequest;
import com.example.entity.Booking;
import com.example.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // Create new booking
    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookingRequest req) {
        Booking b = new Booking();
        b.setHallId(req.getHallId());
        b.setUserId(req.getUserId());
        b.setName(req.getName());
        b.setEmail(req.getEmail());
        b.setMobile(req.getMobile());
        if (req.getDate() != null) b.setDate(java.time.LocalDate.parse(req.getDate()));
        b.setTimeSlot(req.getTimeSlot());
        b.setAmount(req.getAmount());
        b.setUlbId(req.getUlbId());
        Booking saved = bookingService.create(b);
        return ResponseEntity.ok(saved);
    }

    // Get all bookings
    @GetMapping
    public ResponseEntity<List<Booking>> listAll() {
        return ResponseEntity.ok(bookingService.listAll());
    }

    // Get bookings by hall ID and date
    @GetMapping("/hall/{hallId}")
    public ResponseEntity<List<Booking>> byHall(@PathVariable Long hallId, @RequestParam(required = false) String date) {
        if (date == null) {
            return ResponseEntity.ok(bookingService.listAll().stream().filter(b -> b.getHallId().equals(hallId)).toList());
        }
        return ResponseEntity.ok(bookingService.listByHallAndDate(hallId, date));
    }

    // Get booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> get(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getById(id));
    }
}
