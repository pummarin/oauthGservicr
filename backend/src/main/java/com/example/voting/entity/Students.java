package com.example.voting.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Table(name = "STUDENT")
public class Students {

	@Id
	@SequenceGenerator(name = "STUDENT_seq", sequenceName = "STUDENT_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_seq")
	@Column(name = "STUDENTS_ID", unique = true, nullable = true)
	private Long id;
	
	@NotNull
	private String studentId;

	@NotNull
	private String fullName;

	@NotNull
	private String gender;

	@NotNull
    private String identifyNumber;

	@NotNull
    private String s_year;

	@NotNull
	private String email;

	@NotNull
	private String phoneNumber;

	@NotNull
	private String major;

	public Students() {
	}

	public Students(String fullName, String studentId, String gender, String phoneNumber,
					String identifyNumber, String s_year, String email, String major) {
		this.fullName = fullName;
		this.gender = gender;
		this.identifyNumber = identifyNumber;
        this.studentId = studentId;
        this.s_year = s_year;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.major = major;
	}
}