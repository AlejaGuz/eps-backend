package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.MedicalHistory;
import com.example.demo.model.Speciality;
import com.example.demo.repository.IMedicalHistoryRepository;
import com.example.demo.repository.ISpecialityRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class MedicalController {

	@Autowired
	private IMedicalHistoryRepository repository;

	@PostMapping("/medical")
	public MedicalHistory createMedicalH(@RequestBody MedicalHistory mh) {
		System.out.println("entro a create medical");
		return repository.save(mh);
	}
	
	@GetMapping("/medical")
	public List<MedicalHistory> getHistories() {
		
		return repository.findAll();
	}

}
