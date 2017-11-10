// Agradeço a DEUS pelo dom do conhecimento

package com.papejajr.sisude.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exams")
@SequenceGenerator(sequenceName = "seq_exams", name = "ID_SEQUENCE", allocationSize = 1)
public class Exam implements Serializable, Comparable<Exam> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
	private Long id;

	@Column(nullable = false)
	@Size(min = 3, max = 40, message = "Description invalid")
	private String description;

	public Exam(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(Exam o) {
		int comparable = 0;
		if (this.description != null && o.description != null)
			comparable = this.description.compareTo(o.description);
		else if (this.description == null && o.description == null)
			return 0;
		else if (this.description == null)
			return -1;
		else
			return 1;
		return comparable;
	}
}
