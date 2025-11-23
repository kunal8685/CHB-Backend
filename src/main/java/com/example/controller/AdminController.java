package com.example.controller;

import com.example.entity.Booking;
import com.example.entity.BookingStatus;
import com.example.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final BookingService bookingService;

    @PutMapping("/bookings/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable Long id, @RequestHeader(value="X-User-Id", required=false) Long userId) {
        Booking b = bookingService.changeStatus(id, BookingStatus.APPROVED, userId, "Approved by admin");
        return ResponseEntity.ok(b);
    }

    @PutMapping("/bookings/{id}/reject")
    public ResponseEntity<?> reject(@PathVariable Long id, @RequestHeader(value="X-User-Id", required=false) Long userId) {
        Booking b = bookingService.changeStatus(id, BookingStatus.REJECTED, userId, "Rejected by admin");
        return ResponseEntity.ok(b);
    }

    @PutMapping("/bookings/{id}/check")
    public ResponseEntity<?> check(@PathVariable Long id, @RequestHeader(value="X-User-Id", required=false) Long userId) {
        Booking b = bookingService.changeStatus(id, BookingStatus.CHECKED, userId, "Checked by admin");
        return ResponseEntity.ok(b);
    }
}
