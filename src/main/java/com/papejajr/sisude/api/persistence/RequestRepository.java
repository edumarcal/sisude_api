package com.papejajr.sisude.api.persistence;

import org.springframework.data.repository.CrudRepository;

import com.papejajr.sisude.api.model.Request;

public interface RequestRepository  extends CrudRepository<Request, Long> {
	Request findBySchedule(String schedule);
}
