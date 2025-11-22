package com.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.BookingFile;

public interface BookingFileRepository extends JpaRepository<BookingFile, Long> {
    List<BookingFile> findByBookingId(Long bookingId);
}

