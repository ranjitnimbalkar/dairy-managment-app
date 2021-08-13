package com.dairy.managment.app.rest;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

public @Data class RestAddress {	
	
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
