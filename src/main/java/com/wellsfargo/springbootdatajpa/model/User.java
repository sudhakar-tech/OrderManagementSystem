package com.wellsfargo.springbootdatajpa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade={CascadeType.ALL})
	private List<Order> orders;
	 
	 
	    

	

}
