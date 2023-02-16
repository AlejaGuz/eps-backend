package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.model.Schedule;


public interface IScheduleRepository extends JpaRepository<Schedule, Long> {

}
