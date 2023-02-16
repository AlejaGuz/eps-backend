package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;


public interface IPatientRepository extends JpaRepository<Patient, Long> {

}
