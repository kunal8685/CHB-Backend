package com.example.service;

import com.example.entity.BookingFile;
import com.example.repository.BookingFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service @RequiredArgsConstructor
public class FileService {
    private final BookingFileRepository bookingFileRepo;

    @Value("${chb.upload.dir}")
    private String uploadDir;

    public BookingFile storeForBooking(Long bookingId, MultipartFile file, String docType) throws IOException {
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();
        String filename = System.currentTimeMillis()+"_"+file.getOriginalFilename();
        File dest = new File(dir, filename);
        file.transferTo(dest);
        BookingFile bf = new BookingFile();
        bf.setBookingId(bookingId);
        bf.setFilename(filename);
        bf.setUrl("/api/files/download/"+filename);
        bf.setDocType(docType);
        return bookingFileRepo.save(bf);
    }
}
