package com.example.controller;

import com.example.entity.BookingFile;
import com.example.repository.BookingFileRepository;
import com.example.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final BookingFileRepository bookingFileRepo;

    @PostMapping("/upload/booking/{bookingId}")
    public ResponseEntity<?> uploadForBooking(@PathVariable Long bookingId,
                                              @RequestParam("file") MultipartFile file,
                                              @RequestParam(value = "docType", required = false) String docType) {
        try {
            BookingFile bf = fileService.storeForBooking(bookingId, file, docType);
            return ResponseEntity.ok(bf);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed");
        }
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> listFiles(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingFileRepo.findByBookingId(bookingId));
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> download(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("uploads").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) return ResponseEntity.notFound().build();
            String contentType = Files.probeContentType(filePath);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType == null ? "application/octet-stream" : contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
