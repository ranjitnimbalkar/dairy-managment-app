package com.dairy.managment.app.rest;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.dairy.managment.app.enums.EntryType;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RestAdvance {	
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date date;	
	
	@DecimalMin(value = "0.500")
	private double amount;	
	
	@NotNull
	private EntryType entry_type;
	
	private long customer_id;
		
	private Long payment_id;	
	
	private String comment;	

}
