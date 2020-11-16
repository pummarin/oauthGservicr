package com.example.voting.controller;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.Optional;

import com.example.voting.entity.Admin;

import com.example.voting.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    AdminRepository adminRepository;


    @GetMapping("/admins")
    public Collection<Admin> getAllAdmin(){
        return adminRepository.findAll().stream().collect(Collectors.toList());
    }

    /*
    @GetMapping("/admin/{username}/{password}")
    public Admin adminLogin(String username, String password){
        return adminRepository.findByUsernameAndPassword(username, password);
    }
    
    @GetMapping("/admin/{id}")
    public Optional<Admin> adminId(long id){
        return adminRepository.findById(id);
    }
    */
    
}