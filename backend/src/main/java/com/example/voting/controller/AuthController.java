package com.example.voting.controller;


import com.example.voting.entity.Students;
import com.example.voting.entity.Admin;
import com.example.voting.entity.payload.LoginRequest;
import com.example.voting.entity.payload.AdminLoginRequest;
import com.example.voting.repository.AdminRepository;
import com.example.voting.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Optional<Students> students = studentsRepository.findByStudentIdAndIdentifyNumber(loginRequest.getStudentId(), loginRequest.getIdentifyNumber());
        if (students.isPresent()){
            return ResponseEntity.ok().body(students.get());
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UserOrPassword is incorect");
        }
    }

    @PostMapping("/admin/signin")
    public ResponseEntity<?> login(@RequestBody AdminLoginRequest adminloginRequest){
        Optional<Admin> admins = adminRepository.findByUsernameAndPassword(adminloginRequest.getUsername(), adminloginRequest.getPassword());
        if (admins.isPresent()){
            return ResponseEntity.ok().body(admins.get());
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username Or Password is incorect");
        }
    }
}
