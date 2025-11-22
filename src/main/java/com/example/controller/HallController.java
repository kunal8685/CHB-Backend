package com.example.controller;



import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Hall;
import com.example.repository.HallRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/halls")
@RequiredArgsConstructor
public class HallController {
    private final HallRepository hallRepository;

    @GetMapping
    public List<Hall> listHalls() {
        return hallRepository.findAll();
    }
    @PostMapping
    public Hall createHall(@RequestBody Hall hall) {
        return hallRepository.save(hall);
    }

    @GetMapping("/{id}")
    public Hall getHall(@PathVariable Long id) {
        return hallRepository.findById(id).orElseThrow();
    }
}
