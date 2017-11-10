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

import com.papejajr.sisude.api.persistence.DoctorRepository;

import java.util.List;
import java.util.ArrayList;

import com.papejajr.sisude.api.model.Doctor;

@RestController
@RequestMapping("/api")
public class DoctorController {

	@Autowired
	private DoctorRepository repository;

	@RequestMapping(value = "/doctor/", method = RequestMethod.GET)
	public ResponseEntity<List<Doctor>> listAllDoctors() {
		List<Doctor> doctors = new ArrayList<>();
		repository.findAll().forEach(doctors::add);
		if (doctors.isEmpty()) {
			return new ResponseEntity<List<Doctor>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
	}

	@RequestMapping(value = "/doctor/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDoctor(@PathVariable("id") long id) {
		Doctor doctor = repository.findOne(id);
		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
	}

	@RequestMapping(value = "/doctor/", method = RequestMethod.POST)
	public ResponseEntity<?> createDoctor(@RequestBody Doctor doctor, UriComponentsBuilder ucBuilder) {
		repository.save(doctor);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/doctor/{id}").buildAndExpand(doctor.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/doctor/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDoctor(@PathVariable("id") long id, @RequestBody Doctor doctor) {

		Doctor currentDoctor = repository.findOne(id);
		repository.save(doctor);
		return new ResponseEntity<Doctor>(currentDoctor, HttpStatus.OK);
	}

	@RequestMapping(value = "/doctor/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteDoctor(@PathVariable("id") long id) {
		repository.delete(id);
		return new ResponseEntity<Doctor>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/doctor/", method = RequestMethod.DELETE)
	public ResponseEntity<Doctor> deleteAllDoctors() {
		repository.deleteAll();
		return new ResponseEntity<Doctor>(HttpStatus.NO_CONTENT);
	}

}