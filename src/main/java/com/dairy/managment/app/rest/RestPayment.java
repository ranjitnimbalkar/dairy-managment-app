package com.dairy.managment.app.rest;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RestPayment {
	
	private long customerId;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date from_date;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date to_date;
	
	private double total_milk;
	
	private double amount;
	
	private double food_deduction;
	
	private double advance_deduction;
	
	private double total_deduction;
	
	private double pay_amount;
	
	private String comment;	

}
