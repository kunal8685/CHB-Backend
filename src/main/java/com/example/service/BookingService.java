package com.example.service;

import com.example.entity.*;
import com.example.repository.BookingRepository;
import com.example.repository.BookingAuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service @RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepo;
    private final BookingAuditRepository auditRepo;

    public Booking create(Booking b) {
        b.setStatus(BookingStatus.APPLIED);
        b.setCreatedAt(LocalDateTime.now());
        b.setUpdatedAt(LocalDateTime.now());
        Booking saved = bookingRepo.save(b);
        auditRepo.save(new BookingAudit(null, saved.getId(), saved.getUserId(), "APPLIED", "Application created", LocalDateTime.now()));
        return saved;
    }

    public List<Booking> listAll() { return bookingRepo.findAll(); }

    public List<Booking> listByHallAndDate(Long hallId, String dateStr) {
        try { return bookingRepo.findByHallIdAndDate(hallId, LocalDate.parse(dateStr)); }
        catch (Exception e) { return List.of(); }
    }

    public Booking getById(Long id) { return bookingRepo.findById(id).orElseThrow(); }

    @Transactional
    public Booking changeStatus(Long id, BookingStatus newStatus, Long actionBy, String comment) {
        Booking b = getById(id);
        b.setStatus(newStatus);
        b.setUpdatedAt(LocalDateTime.now());
        Booking saved = bookingRepo.save(b);
        auditRepo.save(new BookingAudit(null, id, actionBy, newStatus.name(), comment, LocalDateTime.now()));
        return saved;
    }

    // other helpers
}
