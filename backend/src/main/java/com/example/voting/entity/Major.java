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
public class Major {

    @Id
	@SequenceGenerator(name = "major_seq", sequenceName = "major_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "major_seq")
	private   Long major_id;
	private   String major;

	public Major(){}

	public Major(String major) {
        this.major = major;
    }
}