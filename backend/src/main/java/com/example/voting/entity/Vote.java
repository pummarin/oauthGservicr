package com.example.voting.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

@Data
@Entity
@Table(name = "VOTE")
public class Vote {
    @Id
    @SequenceGenerator(name = "VOTE_seq", sequenceName = "VOTE_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOTE_seq")
    @Column(name = "VOTE_ID", unique = true, nullable = true)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER,targetEntity = Students.class)
    @JoinColumn(name = "STUDENT_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Students students;

    @OneToOne(fetch = FetchType.EAGER,targetEntity = CandidateProfile.class)
    @JoinColumn(name = "CAN_ID", insertable = true)
    @JsonManagedReference
    private @NotNull CandidateProfile candidateProfile;


    private @NotNull Date voteTime;

    private String hash;

    public Vote() {
    }

    public Vote( Students students, CandidateProfile candidateProfile,String hash) {
        this.voteTime = new Date();
        this.students = students;
        this.candidateProfile = candidateProfile;
        this.hash = hash;
    }
}