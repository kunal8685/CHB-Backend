package com.example.controller;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BookingRequest;
import com.example.dto.BookingResponse;
import com.example.entity.Booking;
import com.example.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/bookings")
    public ResponseEntity<?> createBooking(@Validated @RequestBody BookingRequest req) {
        try {
            Booking b = bookingService.createBooking(req);
            return ResponseEntity.status(HttpStatus.CREATED).body(toDto(b));
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(ex.getMessage()));
        }
    }

    @GetMapping("/bookings")
    public List<BookingResponse> listAll() {
        return bookingService.listAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @GetMapping("/bookings/hall/{hallId}")
    public List<BookingResponse> listForHall(@PathVariable Long hallId) {
        return bookingService.listByHall(hallId).stream().map(this::toDto).collect(Collectors.toList());
    }

    private BookingResponse toDto(Booking b) {
        return new BookingResponse(b.getId(), b.getHallId(), b.getName(), b.getEmail(), b.getMobile(), b.getDate(), b.getTimeSlot(), b.getStatus());
    }

    // simple error wrapper
    record ErrorResponse(String message) {}
}
