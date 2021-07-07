package com.wellsfargo.springbootdatajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.springbootdatajpa.exceptions.RecordNotFoundException;
import com.wellsfargo.springbootdatajpa.model.User;
import com.wellsfargo.springbootdatajpa.service.UserServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User users = null;
		users = userServiceImpl.addUser(user);
		if (users == null) {
			throw new RecordNotFoundException("users is not creted : " + users);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/getUsers")
	public User getUsers() {
		return userServiceImpl.getUsers();
	}
}
