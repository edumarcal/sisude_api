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
@Table(name = "users")
@SequenceGenerator(sequenceName="seq_users", name="ID_SEQUENCE", allocationSize=1)
public class User implements Serializable, Comparable<User> {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator= "ID_SEQUENCE")
	private Long id;
	
	@Column(nullable = false)
	@Size(min = 3, max = 40, message = "Name invalid")
	private String name;
	
	@Column(nullable = false)
	@Size(min = 3, max = 40, message = "Login invalid")
	private String login;
	
	@Column(nullable = false)
	@Size(min = 5, max = 40, message = "Password invalid")
	private String password;

	public User(String name, String login, String password) {
		this.name = name;
		this.login = login;
		this.password = password;
	}

	@Override
	public int compareTo(User o) {
		int comparable = 0;
		if (this.login != null && o.login != null)
			comparable = this.login.compareTo(o.login);
		else if (this.login == null && o.login == null)
			return 0;
		else if (this.login == null)
			return -1;
		else
			return 1;
		return comparable;
	}
}
