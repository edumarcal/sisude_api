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
@Table(name = "doctors")
@SequenceGenerator(sequenceName="seq_doctors", name="ID_SEQUENCE", allocationSize=1)
public class Doctor implements Serializable, Comparable<Doctor> {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator= "ID_SEQUENCE")
	private Long id;
	
	@Column(nullable = false)
	@Size(min = 3, max = 40, message = "Name invalid")
	private String name;
	
	@Column(nullable = false)
	@Size(min = 5, max = 40, message = "CRM invalid")
	private String crm;

	public Doctor(String name, String crm) {
		this.name = name;
		this.crm = crm;
	}

	@Override
	public int compareTo(Doctor o) {
		int comparable = 0;
		if (this.name != null && o.name != null)
			comparable = this.name.compareTo(o.name);
		else if (this.name == null && o.name == null)
			return 0;
		else if (this.name == null)
			return -1;
		else
			return 1;
		return comparable;
	}
}
