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

import com.papejajr.sisude.api.persistence.RequestRepository;

import java.util.List;
import java.util.ArrayList;

import com.papejajr.sisude.api.model.Request;

@RestController
@RequestMapping("/api")
public class RequestController {

	@Autowired
	private RequestRepository repository;

	@RequestMapping(value = "/request/", method = RequestMethod.GET)
	public ResponseEntity<List<Request>> listAllRequests() {
		List<Request> requests = new ArrayList<>();
		repository.findAll().forEach(requests::add);
		if (requests.isEmpty()) {
			return new ResponseEntity<List<Request>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Request>>(requests, HttpStatus.OK);
	}

	@RequestMapping(value = "/request/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getRequest(@PathVariable("id") long id) {
		Request request = repository.findOne(id);
		return new ResponseEntity<Request>(request, HttpStatus.OK);
	}

	@RequestMapping(value = "/request/", method = RequestMethod.POST)
	public ResponseEntity<?> createRequest(@RequestBody Request request, UriComponentsBuilder ucBuilder) {
		repository.save(request);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/request/{id}").buildAndExpand(request.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/request/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRequest(@PathVariable("id") long id, @RequestBody Request request) {

		Request currentRequest = repository.findOne(id);
		repository.save(request);
		return new ResponseEntity<Request>(currentRequest, HttpStatus.OK);
	}

	@RequestMapping(value = "/request/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRequest(@PathVariable("id") long id) {
		repository.delete(id);
		return new ResponseEntity<Request>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/request/", method = RequestMethod.DELETE)
	public ResponseEntity<Request> deleteAllRequests() {
		repository.deleteAll();
		return new ResponseEntity<Request>(HttpStatus.NO_CONTENT);
	}

}