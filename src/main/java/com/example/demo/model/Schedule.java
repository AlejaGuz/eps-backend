package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "schedules")
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "date")
	private Date date;
	
	@OneToOne
	private Doctor idDoctor;
	
	@OneToOne
	private Patient idPatient;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Doctor getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(Doctor idDoctor) {
		this.idDoctor = idDoctor;
	}

	public Patient getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(Patient idPatient) {
		this.idPatient = idPatient;
	}

	public Schedule() {
		
	}
	
	

}
