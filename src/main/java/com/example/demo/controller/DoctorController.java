package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Doctor;
import com.example.demo.repository.IDoctorRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class DoctorController {

	@Autowired
	private IDoctorRepository repository;

	
	@GetMapping("/doctors")
	public List<Doctor> getDoctors() {
		return repository.findAll();
	}
	
	@GetMapping("/doctors/{idSpeciality}")
	public List<Doctor> getDoctorsBySpeciality(@PathVariable Long idSpeciality) {
		
		List<Doctor> d = repository.findAll();
		System.out.println("entro al getDoctorBySpeciality");
		
		List<Doctor> dIdSpeciality = new ArrayList<Doctor>();
		
		for (Doctor doctor : d) {
			if(doctor.getSpeciality().getId()== idSpeciality) {

				dIdSpeciality.add(doctor);
				
			}
		}
		
		
		
		return dIdSpeciality;
	}
	
	@PostMapping("/doctors/post")
	public Doctor createDoctor(@RequestBody Doctor d) {
		
		System.out.println("entro a guardar doctor");
		return repository.save(d);
	}
	
	@GetMapping("/doctors/id/{id}")
	public Optional<Doctor> getDoctorsById(@PathVariable Long id){
		
		System.out.println("entro a get doctor by id");
		
		return repository.findById(id)/*.orElseThrow(() -> new ResourceNotFoundException("Doctor doesnt exist"))*/;
		
	}

}
