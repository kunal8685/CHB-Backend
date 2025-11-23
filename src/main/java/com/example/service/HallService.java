package com.example.service;

import com.example.entity.Hall;
import com.example.repository.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class HallService {
    private final HallRepository hallRepo;
    public List<Hall> getAll() { return hallRepo.findAll(); }
    public Hall get(Long id) { return hallRepo.findById(id).orElseThrow(); }
    public Hall create(Hall h) { return hallRepo.save(h); }
}
