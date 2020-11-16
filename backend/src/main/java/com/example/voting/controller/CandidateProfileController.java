package com.example.voting.controller;


import com.example.voting.entity.*;
import com.example.voting.entity.payload.CandidateProfilePayload;
import com.example.voting.entity.payload.FindCandidate;
import com.example.voting.repository.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CandidateProfileController {

    @Autowired
    private CandidateProfileRepository candidateProfileRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    private StudentsRepository studentsRepository;

    public CandidateProfileController(CandidateProfileRepository candidateProfileRepository) {
        this.candidateProfileRepository = candidateProfileRepository;
    }

    @GetMapping("/canprofile")
    public Collection<CandidateProfile> getAllCandidateProfile() {
        return candidateProfileRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/canprofile2")
    public Collection<CandidateProfile> getAllCandidateProfileByYear(@RequestParam int year) {
        return candidateProfileRepository.findByYear(year);
    }

    @PostMapping("/canp/student")
    public ResponseEntity<?> findCanwherestudent(@RequestBody FindCandidate payload) {
        Optional<CandidateProfile> candidateProfile = candidateProfileRepository.findByStudentId(payload.getStudentId());
        if (candidateProfile.isPresent()) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.ok().body(false);
        }
//        return ResponseEntity.badRequest().body("Error: Incorrect Student_Id!");
    }

    @PostMapping("/canp")
    public CandidateProfile newCandidateProfile(@RequestBody CandidateProfilePayload canp) {

        CandidateProfile cp = new CandidateProfile();


        Optional<Major> major = majorRepository.findById(canp.getMajor_id());
        Optional<Gender> gender = genderRepository.findById(canp.getGender_id());
        Optional<Admin> admin = adminRepository.findById(canp.getAdmin_id());
        System.out.println(canp.getC_name());

        cp.setTitle_name(canp.getTitle_name());
        cp.setC_name(canp.getC_name());
        cp.setBirthday(canp.getBirthday());
        cp.setTelephone(canp.getTelephone());
        cp.setStudentId(canp.getStudent_id());
        cp.setYear(canp.getYear());
        cp.setGrade(canp.getGrade());
        cp.setArchivement(canp.getArchivement());
        cp.setC_number(canp.getC_number());
        cp.setPurpose(canp.getPurpose());

        cp.setMajor(major.get());
        cp.setGender(gender.get());
        cp.setAdmin(admin.get());
        return candidateProfileRepository.save(cp);


    }

}