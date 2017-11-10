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

import com.papejajr.sisude.api.persistence.ExamRepository;

import java.util.List;
import java.util.ArrayList;

import com.papejajr.sisude.api.model.Exam;

@RestController
@RequestMapping("/api")
public class ExamController {

	@Autowired
	private ExamRepository repository;

	@RequestMapping(value = "/exam/", method = RequestMethod.GET)
	public ResponseEntity<List<Exam>> listAllExams() {
		List<Exam> exams = new ArrayList<>();
		repository.findAll().forEach(exams::add);
		if (exams.isEmpty()) {
			return new ResponseEntity<List<Exam>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Exam>>(exams, HttpStatus.OK);
	}

	@RequestMapping(value = "/exam/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getExam(@PathVariable("id") long id) {
		Exam exam = repository.findOne(id);
		return new ResponseEntity<Exam>(exam, HttpStatus.OK);
	}

	@RequestMapping(value = "/exam/", method = RequestMethod.POST)
	public ResponseEntity<?> createExam(@RequestBody Exam exam, UriComponentsBuilder ucBuilder) {
		repository.save(exam);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/exam/{id}").buildAndExpand(exam.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/exam/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateExam(@PathVariable("id") long id, @RequestBody Exam exam) {

		Exam currentExam = repository.findOne(id);
		repository.save(exam);
		return new ResponseEntity<Exam>(currentExam, HttpStatus.OK);
	}

	@RequestMapping(value = "/exam/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteExam(@PathVariable("id") long id) {
		repository.delete(id);
		return new ResponseEntity<Exam>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/exam/", method = RequestMethod.DELETE)
	public ResponseEntity<Exam> deleteAllExams() {
		repository.deleteAll();
		return new ResponseEntity<Exam>(HttpStatus.NO_CONTENT);
	}

}