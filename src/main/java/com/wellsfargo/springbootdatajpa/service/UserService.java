package com.wellsfargo.springbootdatajpa.service;

import com.wellsfargo.springbootdatajpa.model.User;


public interface UserService {
	
	public User addUser(User user);
	public User findByuserId(int userId);
	public User getUsers();
	public User findByUserName(String userName);
}
