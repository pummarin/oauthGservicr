package com.example.voting.entity;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity

@Getter
@Setter
public class Gender {

    @Id
	@SequenceGenerator(name = "gender_seq", sequenceName = "gender_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gender_seq")
	private   Long gender_id;
	private   String gender;

	public Gender(){}

	public Gender(String gender) {
        this.gender = gender;
    }
}