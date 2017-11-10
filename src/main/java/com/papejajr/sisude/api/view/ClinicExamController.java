package com.papejajr.sisude.api.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.papejajr.sisude.api.persistence.ClinicExamRepository;

import java.util.List;
import java.util.ArrayList;

import com.papejajr.sisude.api.model.ClinicExam;

@RestController
@RequestMapping("/api")
public class ClinicExamController {

	@Autowired
	private ClinicExamRepository repository;

	@RequestMapping(value = "/clinic_exam/", method = RequestMethod.GET)
	public ResponseEntity<List<ClinicExam>> listAllclinicExams() {
		List<ClinicExam> clinicExams = new ArrayList<>();
		repository.findAll().forEach(clinicExams::add);
		if (clinicExams.isEmpty()) {
			return new ResponseEntity<List<ClinicExam>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ClinicExam>>(clinicExams, HttpStatus.OK);
	}

	@RequestMapping(value = "/clinic_exam/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getclinicExam(@PathVariable("id") long id) {
		ClinicExam clinicExam = repository.findOne(id);
		return new ResponseEntity<ClinicExam>(clinicExam, HttpStatus.OK);
	}

	@RequestMapping(value = "/clinic_exam/", method = RequestMethod.POST)
	public ResponseEntity<?> createclinicExam(@RequestBody ClinicExam clinicExam, UriComponentsBuilder ucBuilder) {
		repository.save(clinicExam);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/clinic_exam/{id}").buildAndExpand(clinicExam.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/clinic_exam/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateclinicExam(@PathVariable("id") long id, @RequestBody ClinicExam clinicExam) {

		ClinicExam currentclinicExam = repository.findOne(id);
		repository.save(clinicExam);
		return new ResponseEntity<ClinicExam>(currentclinicExam, HttpStatus.OK);
	}

	@RequestMapping(value = "/clinic_exam/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteclinicExam(@PathVariable("id") long id) {
		repository.delete(id);
		return new ResponseEntity<ClinicExam>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/clinic_exam/", method = RequestMethod.DELETE)
	public ResponseEntity<ClinicExam> deleteAllclinicExams() {
		repository.deleteAll();
		return new ResponseEntity<ClinicExam>(HttpStatus.NO_CONTENT);
	}

}