package com.papejajr.sisude.api.persistence;

import org.springframework.data.repository.CrudRepository;

import com.papejajr.sisude.api.model.User;

public interface UserRepository  extends CrudRepository<User, Long> {
	User findByName(String login);
}