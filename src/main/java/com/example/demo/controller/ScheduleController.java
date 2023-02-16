package com.example.demo.controller;


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
import com.example.demo.model.Schedule;
import com.example.demo.repository.IScheduleRepository;


@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class ScheduleController {

	@Autowired
	private IScheduleRepository repository;


	@GetMapping("/schedules")
	public List<Schedule> getSchedules() {

		return repository.findAll();
	}
	
	@PostMapping("/schedules")
	public Schedule createSchedule(@RequestBody Schedule s) {
		System.out.println("entro a create schedule");
		return repository.save(s);
	}
	
	@GetMapping("/schedules/{id}")
	public Optional<Schedule> getScheduletById(@PathVariable Long id) {
		
		System.out.println("entntro a schedule by Id: "+ id);
		Optional<Schedule> sc = repository.findById(id);
		System.out.println(sc.isPresent());
		return sc;
	}

}
