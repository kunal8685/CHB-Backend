package com.example.controller;

import com.example.entity.Booking;
import com.example.entity.BookingStatus;
import com.example.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final BookingService bookingService;

    @PostMapping("/pay/{bookingId}")
    public ResponseEntity<?> pay(@PathVariable Long bookingId, @RequestParam String paymentRef,
                                 @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        Booking b = bookingService.changeStatus(bookingId, BookingStatus.PAID, userId, "Paid via " + paymentRef);
        // Simple receipt text generation
        String receipt = "Receipt\nBookingId: " + b.getId() + "\nAmount: " + b.getAmount() + "\nStatus: " + b.getStatus();
        byte[] bytes = receipt.getBytes(StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=receipt_" + b.getId() + ".txt")
                .body(bytes);
    }
}
