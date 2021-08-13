package com.dairy.managment.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dairy.managment.app.entity.Advance;
import com.dairy.managment.app.rest.RestAdvance;
import com.dairy.managment.app.service.AdvanceService;

@RestController
@RequestMapping("/advance/{customerId}")
public class AdvanceController {
	
	@Autowired
	AdvanceService advanceService;

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Advance> create(@Valid @RequestBody RestAdvance advanceEntry,
													 @PathVariable String customerId){	
		
		advanceEntry.setCustomer_id(Long.valueOf(customerId));	
		
		Advance addAdance = advanceService.addAdance(advanceEntry);
		
		return new ResponseEntity<Advance>(addAdance, null, HttpStatus.CREATED);

	}
	
}
