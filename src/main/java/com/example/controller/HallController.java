package com.example.controller;

import com.example.entity.Hall;
import com.example.service.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/halls")
@RequiredArgsConstructor
public class HallController {
    private final HallService hallService;

    @GetMapping
    public ResponseEntity<List<Hall>> all() { return ResponseEntity.ok(hallService.getAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Hall> get(@PathVariable Long id) { return ResponseEntity.ok(hallService.get(id)); }

    @PostMapping
    public ResponseEntity<Hall> create(@RequestBody Hall hall) { return ResponseEntity.ok(hallService.create(hall)); }
}
