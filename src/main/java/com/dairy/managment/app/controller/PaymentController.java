package com.dairy.managment.app.controller;

import javax.naming.OperationNotSupportedException;
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

import com.dairy.managment.app.entity.Payment;
import com.dairy.managment.app.rest.RestPayment;
import com.dairy.managment.app.service.PaymentService;

@RestController
@RequestMapping("/payments/{customerId}")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Payment> createPayment(@Valid @RequestBody RestPayment paymentData,
													 @PathVariable String customerId) throws OperationNotSupportedException {
		
		paymentData.setCustomerId(Long.valueOf(customerId));
		
		Payment addPaymentEntry = paymentService.addPaymentEntry(paymentData);
		
		return new ResponseEntity<Payment>(addPaymentEntry, HttpStatus.CREATED);
	}
	

}
