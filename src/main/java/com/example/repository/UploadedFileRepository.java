package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.UploadedFile;

import java.util.List;

public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {
    List<UploadedFile> findByBookingId(Long bookingId);
}
