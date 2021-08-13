package com.dairy.managment.app.rest;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.dairy.managment.app.enums.EntryType;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RestFood {
	
	private long customer_id;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date date;
	
	@NotNull
	private Double quantity;
	
	@NotNull
	private EntryType entry_type;
	
	@NotNull
	private Double amount;
	
	private String comment;
	
	
}
