package com.example.demo.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "records")
public class Record {
	
	@Override
	public String toString() {
		return "Record [id=" + id + ", date=" + date + ", idDoctor=" + idDoctor + ", description=" + description + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "date")
	private Date date;
	
	@OneToOne
	private Doctor idDoctor;

	@Column(name = "description")
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Record() {
		
	}
	
	
	
}
