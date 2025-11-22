package com.example.service;




import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.BookingRequest;
import com.example.entity.Booking;
import com.example.repository.BookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking createBooking(BookingRequest req) {
        // prevent double-booking: check same hall/date/timeSlot that is not REJECTED
        List<Booking> conflicts = bookingRepository.findByHallIdAndDateAndTimeSlot(req.getHallId(), req.getDate(), req.getTimeSlot());
        boolean anyActive = conflicts.stream()
                .anyMatch(b -> !"REJECTED".equalsIgnoreCase(b.getStatus()));
        if (anyActive) {
            throw new IllegalStateException("Time slot already booked for selected date.");
        }

        Booking b = Booking.builder()
                .hallId(req.getHallId())
                .name(req.getName())
                .email(req.getEmail())
                .mobile(req.getMobile())
                .date(req.getDate())
                .timeSlot(req.getTimeSlot())
                .status("PENDING")
                .build();
        return bookingRepository.save(b);
    }

    public List<Booking> listAll() {
        return bookingRepository.findAll();
    }

    public List<Booking> listByHall(Long hallId) {
        return bookingRepository.findByHallId(hallId);
    }

    public Booking approve(Long id) {
        Booking b = bookingRepository.findById(id).orElseThrow();
        b.setStatus("APPROVED");
        return bookingRepository.save(b);
    }

    public Booking reject(Long id) {
        Booking b = bookingRepository.findById(id).orElseThrow();
        b.setStatus("REJECTED");
        return bookingRepository.save(b);
    }
}
