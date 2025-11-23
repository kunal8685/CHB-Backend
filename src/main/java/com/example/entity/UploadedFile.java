package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "uploaded_files")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UploadedFile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String url;
}
