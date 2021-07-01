package com.wellsfargo.springbootdatajpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wellsfargo.springbootdatajpa.model.User;
import com.wellsfargo.springbootdatajpa.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService{
	
	User user = null;
    @Autowired
    private UserRepository userRepository;
    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user = userRepository.findByUserName(username);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
	}

}
