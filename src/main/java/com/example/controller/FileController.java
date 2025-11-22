package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.UploadedFile;
import com.example.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    // Upload document
    @PostMapping("/{bookingId}/upload")
    public UploadedFile uploadDocument(
            @PathVariable Long bookingId,
            @RequestParam("file") MultipartFile file
    ) throws Exception {
        return fileService.upload(bookingId, file);
    }

    // Get all documents for a booking
    @GetMapping("/{bookingId}/documents")
    public List<UploadedFile> getDocuments(@PathVariable Long bookingId) {
        return fileService.getFiles(bookingId);
    }
}
