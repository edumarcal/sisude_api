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

import com.papejajr.sisude.api.persistence.ClinicRepository;

import java.util.List;
import java.util.ArrayList;

import com.papejajr.sisude.api.model.Clinic;

@RestController
@RequestMapping("/api")
public class ClinicController {

	@Autowired
	private ClinicRepository repository;

	@RequestMapping(value = "/clinic/", method = RequestMethod.GET)
	public ResponseEntity<List<Clinic>> listAllClinics() {
		List<Clinic> clinics = new ArrayList<>();
		repository.findAll().forEach(clinics::add);
		if (clinics.isEmpty()) {
			return new ResponseEntity<List<Clinic>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Clinic>>(clinics, HttpStatus.OK);
	}

	@RequestMapping(value = "/clinic/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getClinic(@PathVariable("id") long id) {
		Clinic clinicExam = repository.findOne(id);
		return new ResponseEntity<Clinic>(clinicExam, HttpStatus.OK);
	}

	@RequestMapping(value = "/clinic/", method = RequestMethod.POST)
	public ResponseEntity<?> createClinic(@RequestBody Clinic clinicExam, UriComponentsBuilder ucBuilder) {
		repository.save(clinicExam);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/clinic/{id}").buildAndExpand(clinicExam.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/clinic/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateClinic(@PathVariable("id") long id, @RequestBody Clinic clinicExam) {

		Clinic currentclinicExam = repository.findOne(id);
		repository.save(clinicExam);
		return new ResponseEntity<Clinic>(currentclinicExam, HttpStatus.OK);
	}

	@RequestMapping(value = "/clinic/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteClinic(@PathVariable("id") long id) {
		repository.delete(id);
		return new ResponseEntity<Clinic>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/clinic/", method = RequestMethod.DELETE)
	public ResponseEntity<Clinic> deleteAllClinics() {
		repository.deleteAll();
		return new ResponseEntity<Clinic>(HttpStatus.NO_CONTENT);
	}

}