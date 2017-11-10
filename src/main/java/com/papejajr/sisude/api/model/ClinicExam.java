// Agradeço a DEUS pelo dom do conhecimento

package com.papejajr.sisude.api.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "clinics_exams")
@SequenceGenerator(sequenceName = "seq_clinics_exams", name = "ID_SEQUENCE", allocationSize = 1)
public class ClinicExam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "clinic_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "clinic_fk"))
	private Clinic clinic;

	@ManyToOne
	@JoinColumn(name = "exam_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "exam_fk"))
	private Exam exam;

	@ManyToOne
	@JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "doctor_fk"))
	private Doctor doctor;

	public ClinicExam(Clinic clinic, Exam exam, Doctor doctor) {
		super();
		this.clinic = clinic;
		this.exam = exam;
		this.doctor = doctor;
	}

}