package com.wellsfargo.springbootdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.springbootdatajpa.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUserName(String username);
}
