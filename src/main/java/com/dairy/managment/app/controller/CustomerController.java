package com.dairy.managment.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dairy.managment.app.entity.Customer;
import com.dairy.managment.app.rest.RestCustomer;
import com.dairy.managment.app.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService custService;

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Customer> create(@Valid @RequestBody RestCustomer customer){		
			
		Customer addedCustomer = custService.addCustomer(customer);
		
		return new ResponseEntity<Customer>(addedCustomer, null, HttpStatus.CREATED);

	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCutomerDeatails(){
		return null;
	}
	
}
