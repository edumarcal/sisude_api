package com.papejajr.sisude.api.persistence;

import org.springframework.data.repository.CrudRepository;

import com.papejajr.sisude.api.model.Doctor;

public interface DoctorRepository  extends CrudRepository<Doctor, Long> {
	Doctor findByName(String name);
}
