package com.wellsfargo.springbootdatajpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.springbootdatajpa.model.User;
import com.wellsfargo.springbootdatajpa.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User addUser(User user) {	
		return userRepository.save(user);
	}

	@Override
	public User findByuserId(int userId) {
		return userRepository.findById(userId).get();
	}

	@Override
	public User getUsers() {
		return (User) userRepository.findAll();
	}

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	

}
