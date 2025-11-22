package com.example.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.UploadedFile;
import com.example.repository.UploadedFileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

    private final UploadedFileRepository repo;

    private final String uploadDir = "uploads"; // root folder

    public UploadedFile upload(Long bookingId, MultipartFile file) throws IOException {

        File folder = new File(uploadDir);
        if (!folder.exists()) folder.mkdirs();

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File savedFile = new File(folder, fileName);

        file.transferTo(savedFile);

        UploadedFile uf = UploadedFile.builder()
                .bookingId(bookingId)
                .fileName(file.getOriginalFilename())
                .filePath(savedFile.getAbsolutePath())
                .contentType(file.getContentType())
                .fileSize(file.getSize())
                .build();

        return repo.save(uf);
    }

    public java.util.List<UploadedFile> getFiles(Long bookingId) {
        return repo.findByBookingId(bookingId);
    }
}
