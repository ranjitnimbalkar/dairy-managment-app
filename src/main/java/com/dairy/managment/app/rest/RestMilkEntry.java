package com.dairy.managment.app.rest;

import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.dairy.managment.app.enums.MilkTime;

import lombok.Data;

@Data
public class RestMilkEntry {	
	
	private Date date;
	
	@NotNull
	private MilkTime milk_time;
	
	private long customerId;

	private long animal_id;	
	
	@DecimalMin(value = "0.500")	
	private double liters;	
	
	@DecimalMin(value = "5.00")
	@DecimalMax(value = "15.00")
	private double snf;	
	
	@DecimalMin(value = "3.00")
	@DecimalMax(value = "15.00")
	private double fat;	
	

	@DecimalMin(value = "25.00")
	@DecimalMax(value = "32.00")
	private double degree;	
	
	@DecimalMin(value = "1.00")	
	private double rate;
	
}
