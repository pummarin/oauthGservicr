package com.example.voting.repository;

import com.example.voting.entity.Students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface StudentsRepository extends JpaRepository<Students, Long>{

//    Students findById(long id);
    Optional<Students> findByStudentIdAndIdentifyNumber(String studentId, String identifyNumber);
    Optional<Students> findByStudentId(String studentId);
}