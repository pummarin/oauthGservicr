package com.example.voting.repository;

import com.example.voting.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface AdminRepository extends JpaRepository<Admin, Long> {
    //Optional<Admin> findById(Long admin_id);

    Optional <Admin> findByUsernameAndPassword(String username, String password);
}
