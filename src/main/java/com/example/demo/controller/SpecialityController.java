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
import com.example.demo.model.Speciality;
import com.example.demo.repository.ISpecialityRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class SpecialityController {

	@Autowired
	private ISpecialityRepository repository;

	
	@GetMapping("/speciality")
	public List<Speciality> getSpecialities() {
		return repository.findAll();
	}
	
	@GetMapping("/speciality/{id}")
	public ResponseEntity<Speciality> getSpecialitiesById(@PathVariable Long id) {
		
		Speciality s = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Speciality no exists"));
		
		return ResponseEntity.ok(s);
	}
	
	@PostMapping("/speciality")
	public Speciality createSpeciality(@RequestBody Speciality s) {
		System.out.println("entro en backend a crear speciality");
		return repository.save(s);
	}

}
