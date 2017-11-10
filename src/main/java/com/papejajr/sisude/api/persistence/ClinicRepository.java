package com.papejajr.sisude.api.persistence;

import org.springframework.data.repository.CrudRepository;

import com.papejajr.sisude.api.model.Clinic;

public interface ClinicRepository  extends CrudRepository<Clinic, Long> {
	Clinic findByName(String name);
}
