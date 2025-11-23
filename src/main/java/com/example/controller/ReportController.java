package com.example.controller;

import com.example.entity.Booking;
import com.example.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController @RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final BookingRepository bookingRepo;

    @GetMapping("/by-date")
    public ResponseEntity<?> byDate(@RequestParam String date) {
        LocalDate d = LocalDate.parse(date);
        List<Booking> list = bookingRepo.findAll().stream().filter(b->d.equals(b.getDate())).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/revenue")
    public ResponseEntity<?> revenue(@RequestParam String from, @RequestParam String to) {
        LocalDate f = LocalDate.parse(from);
        LocalDate t = LocalDate.parse(to);
        double sum = bookingRepo.findAll().stream()
                .filter(b-> b.getDate()!=null && !b.getDate().isBefore(f) && !b.getDate().isAfter(t))
                .mapToDouble(b-> b.getAmount()!=null?b.getAmount():0.0).sum();
        return ResponseEntity.ok(sum);
    }
}
