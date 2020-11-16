package com.example.voting.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@Table(name="CandidateProfile")


public class CandidateProfile {
    @Id
    @SequenceGenerator(name="candidate_profile_seq",sequenceName="candidate_profile_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="candidate_profile_seq")  
    @Column(name = "CAN_ID", unique = true, nullable = true)
    private  Long can_id;
    
    @NotNull
    private String title_name;
    @NotNull
    private  String c_name;
    @NotNull
    private  String birthday;
    @NotNull
    private  String telephone;
    @NotNull
    private String studentId;
    @NotNull
    private int year;
    @NotNull
    private  String grade;
    @NotNull
    private  String archivement;
    @NotNull
    private  int c_number;
    @NotNull
    private  String purpose;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Major.class)
    @JoinColumn(name = "MAJOR_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Major major; 

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Gender.class)
    @JoinColumn(name = "GENDER_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Gender gender; 

    @OneToOne(fetch = FetchType.EAGER,targetEntity = Admin.class)
    @JoinColumn(name = "ADMIN_ID", insertable = true)
    @JsonManagedReference
    private @NotNull Admin admin;

    private int points;
    
    public CandidateProfile(){}

    public CandidateProfile(String title_name, String c_name, String birthday, String telephone, String student_id, int year, String grade,
                            String archivement,int c_number, String purpose, Major major, Gender gender, Admin admin, int points) {
        this.title_name = title_name;
        this.c_name = c_name;
        this.birthday = birthday;
        this.telephone = telephone;
        this.studentId = student_id;
        this.major = major;
        this.year = year;
        this.grade = grade;
        this.archivement = archivement;
        this.c_number = c_number;
        this.purpose = purpose;
        this.gender = gender;
        this.admin = admin;
        this.points = points;
		
	}
}