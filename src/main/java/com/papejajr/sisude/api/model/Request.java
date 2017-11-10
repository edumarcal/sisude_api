// Agradeço a DEUS pelo dom do conhecimento

package com.papejajr.sisude.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "requests")
@SequenceGenerator(sequenceName = "seq_requests", name = "ID_SEQUENCE", allocationSize = 1)
public class Request implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "user_fk"))
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "clinic_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "clinic_fk"))
	private Clinic clinic;

	@ManyToOne
	@JoinColumn(name = "exam_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "exam_fk"))
	private Exam exam;

	@ManyToOne
	@JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "doctor_fk"))
	private Doctor doctor;

	@Column(nullable = false)
	@Size(min = 5, max = 40, message = "Schedule invalid")
	private String schedule;
	
	public Request(User user, Clinic clinic, Exam exam, Doctor doctor, String schedule) {
		this.user = user;
		this.clinic = clinic;
		this.exam = exam;
		this.doctor = doctor;
		this.schedule = schedule;
	}

}