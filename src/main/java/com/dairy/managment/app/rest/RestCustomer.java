package com.dairy.managment.app.rest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

public @Data class RestCustomer {	
	
	@NotEmpty
	private String name;	
	
	@NotEmpty
	@Pattern(regexp = "^\\d{10}$")
	private String mobile;	
	
	private double food_balance;
	
	private double advance_balance;
	
	private RestAddress address;
}
