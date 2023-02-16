package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Record;



public interface IRecordRepository extends JpaRepository<Record, Long> {

}
