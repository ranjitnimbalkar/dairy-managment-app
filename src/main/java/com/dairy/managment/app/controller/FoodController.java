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

import com.dairy.managment.app.entity.Food;
import com.dairy.managment.app.rest.RestFood;
import com.dairy.managment.app.service.FoodService;

@RestController
@RequestMapping("/foods/{customerId}")
public class FoodController {
	
	@Autowired
	FoodService foodService;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Food> createFood(@Valid @RequestBody RestFood foodEntry,
			 @PathVariable String customerId) throws OperationNotSupportedException {
		
		foodEntry.setCustomer_id(Long.valueOf(customerId));
		
		Food savedFoodEntry = foodService.addFood(foodEntry);
		
		return new ResponseEntity<Food>(savedFoodEntry, HttpStatus.CREATED);
}

}
