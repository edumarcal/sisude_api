package com.papejajr.sisude.api.persistence;

import org.springframework.data.repository.CrudRepository;

import com.papejajr.sisude.api.model.Exam;

public interface ExamRepository  extends CrudRepository<Exam, Long> {
	Exam findByDescription(String name);
}
