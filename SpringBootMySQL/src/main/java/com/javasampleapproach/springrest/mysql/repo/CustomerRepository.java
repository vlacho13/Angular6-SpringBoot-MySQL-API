package com.javasampleapproach.springrest.mysql.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.javasampleapproach.springrest.mysql.model.User;

public interface CustomerRepository extends CrudRepository<User, Long> {
	List<User> findByEmail(String email);
}
