package com.example.repository;
import com.example.entity.BookingFile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BookingFileRepository extends JpaRepository<BookingFile, Long> {
    List<BookingFile> findByBookingId(Long bookingId);
}
