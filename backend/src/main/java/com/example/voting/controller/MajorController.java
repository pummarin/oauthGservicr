package com.example.voting.controller;

import com.example.voting.entity.Major;
import com.example.voting.repository.MajorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class MajorController {

    @Autowired
    private MajorRepository majorRepository;

    public MajorController(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    @GetMapping("/major")
    public Collection<Major> Major() {
        return majorRepository.findAll().stream().collect(Collectors.toList());
    }
}