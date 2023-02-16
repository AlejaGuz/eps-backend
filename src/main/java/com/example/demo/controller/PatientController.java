package com.example.demo.controller;

import java.util.ArrayList;
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
import com.example.demo.model.Patient;
import com.example.demo.model.Record;
import com.example.demo.repository.IMedicalHistoryRepository;
import com.example.demo.repository.IPatientRepository;
import com.example.demo.repository.IRecordRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class PatientController {

	@Autowired
	private IPatientRepository repository;
	
	@Autowired
	private IRecordRepository repositoryRec;
	
	@Autowired
	private IMedicalHistoryRepository repositoryMed;
	
	private List<Record> recordsByHistory = new ArrayList<>();


	@GetMapping("/patients")
	public List<Patient> getPatients() {
		return repository.findAll();
	}
	
	@PostMapping("/patients")
	public Patient createPatient(@RequestBody Patient p) {
		System.out.println("entro a create patient");
		return repository.save(p);
	}
	
	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
		Patient p = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient no exists"));
		return ResponseEntity.ok(p);
	}
	
	@GetMapping("/patients/history/{id}")
	public List<Record> getRecordsPatient(@PathVariable Long id) {
		Optional<MedicalHistory> mh = repositoryMed.findById(id);
		
		mh.ifPresent(m -> recordsByHistory= m.getRecords());
		
		System.out.println("Fecha record: "+recordsByHistory.get(0).getDate());
		
		return recordsByHistory;
	}
	

}
