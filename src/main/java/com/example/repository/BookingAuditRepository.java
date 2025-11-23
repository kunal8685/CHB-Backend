package com.example.repository;
import com.example.entity.BookingAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BookingAuditRepository extends JpaRepository<BookingAudit, Long> {
    List<BookingAudit> findByBookingId(Long bookingId);
}
