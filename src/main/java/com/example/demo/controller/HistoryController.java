package com.example.demo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Doctor;
import com.example.demo.model.MedicalHistory;
import com.example.demo.model.Patient;
import com.example.demo.model.Record;
import com.example.demo.model.Speciality;
import com.example.demo.repository.IMedicalHistoryRepository;
import com.example.demo.repository.IPatientRepository;
import com.example.demo.repository.IRecordRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class HistoryController {

	
	@Autowired
	private IMedicalHistoryRepository repositoryhis;
	
	@Autowired
	private IRecordRepository repositoryRec;
	
	@Autowired
	private IPatientRepository repositorypat;
	
	//private Long idMh;
	private MedicalHistory medHis;
	
	//private List<Record> newRecords = new ArrayList<>();
	
	@PostMapping("/history")
	public List<Record> pos(@RequestBody Map<String, Object> body){
		
		Record record = new Record();
		
		String dateString = (String)((Map<String, Object>)body.get("record")).get("date");
		
		record.setDate(getDateFromStrings(dateString));
		
		String description = (String)((Map<String, Object>)body.get("record")).get("description");
		
		record.setDescription(description);
		
		Map<String, Object> doctor = (Map<String, Object>)((Map<String, Object>)body.get("record")).get("idDoctor");
		
		record.setIdDoctor(getDoctorFromStrings(doctor));
		
		Record r = repositoryRec.save(record);
		
		Optional<Patient> p = repositorypat.findById(((Number)body.get("idPat")).longValue());
		
		p.ifPresent(d -> /*idMh*/medHis= d.getHistory()/*.getId()*/);
		
		System.out.println("Existe Historia clinica: " + medHis);
		
		/*Optional<MedicalHistory> m = repositoryhis.findById(idMh);
		
		m.ifPresent(d -> newRecords = d.getRecords());	
		newRecords.add(record);
		
		m.ifPresent(d -> d.setRecords(newRecords));*/
		
		medHis.getRecords().add(record);
		
		repositoryhis.save(medHis);
		
		return repositoryRec.findAll();
		
	}
	
	public Date getDateFromStrings(String d) {
		
		try {
			
			String [] dateSplit = d.split("T");
			
			String dateSplit2 = dateSplit[1].substring(0, 8);
			
			String dateDefinitivo = dateSplit[0]+" "+dateSplit2;
			
			String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
		
			DateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);
			
			Date date = formatter.parse(dateDefinitivo);
			
			return date;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public Doctor getDoctorFromStrings(Map<String, Object> d) {
		Doctor doc = new Doctor();
		
		try {
			doc.setId(((Number)d.get("id")).longValue());
			doc.setName((String)d.get("name"));
			
			Speciality speciality = new Speciality();
			Map<String, Object> s =(Map<String, Object>)d.get("speciality");
			
			speciality.setId((Long)s.get("id"));
			speciality.setName((String)s.get("name"));
			
			doc.setSpeciality(speciality);
			
		}catch(Exception e) {
			
		}
		
		return doc;
	}

}
