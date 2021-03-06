package com.example.voting.repository;

import com.example.voting.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MajorRepository extends JpaRepository<Major, Long> {
    Major findById(long major_id);
}