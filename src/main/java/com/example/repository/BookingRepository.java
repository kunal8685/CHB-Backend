package com.example.repository;
import com.example.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByHallIdAndDate(Long hallId, LocalDate date);
    List<Booking> findByStatus(String status);
    List<Booking> findByUlbId(Long ulbId);
}
