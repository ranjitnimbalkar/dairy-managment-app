package com.dairy.managment.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "address")
public @Data class Address {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String address_line;	
	
	@NotEmpty
	private String city;	
	
	@NotEmpty
	private String taluka;	
	
	@NotEmpty
	private String district;	
	
	private String state;	
	
	private String country;	
	
	@NotEmpty
	private String pin;

}
