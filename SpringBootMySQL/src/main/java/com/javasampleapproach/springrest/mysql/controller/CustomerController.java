package com.javasampleapproach.springrest.mysql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.springrest.mysql.model.Costumer;
import com.javasampleapproach.springrest.mysql.repo.CostumerRepository;

@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository repository;

	@GetMapping("/Users")
	public List<User> getAllUsers() {
		System.out.println("Get all Users...");

		List<User> Users = new ArrayList<>();
		repository.findAll().forEach(Users::add);

		return Users;
	}

	@PostMapping(value = "/Users/create")
	public User postUser(@RequestBody User User) {

		User _User = repository.save(new User(User.getName(), User.getAge()));
		return _User;
	}

	@DeleteMapping("/Users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
		System.out.println("Delete User with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/Users/delete")
	public ResponseEntity<String> deleteAllUsers() {
		System.out.println("Delete All Users...");

		repository.deleteAll();

		return new ResponseEntity<>("All Users have been deleted!", HttpStatus.OK);
	}

	@GetMapping(value = "Users/age/{age}")
	public List<User> findByAge(@PathVariable int age) {

		List<User> Users = repository.findByAge(age);
		return Users;
	}

	@PutMapping("/Users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User User) {
		System.out.println("Update User with ID = " + id + "...");

		Optional<User> UserData = repository.findById(id);

		if (UserData.isPresent()) {
			User _User = UserData.get();
			_User.setName(User.getName());
			_User.setAge(User.getAge());
			_User.setActive(User.isActive());
			return new ResponseEntity<>(repository.save(_User), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
