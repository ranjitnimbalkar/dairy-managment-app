package com.dairy.managment.app.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PaymentPrimaryKey implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public PaymentPrimaryKey() {
		super();
	}

	private long customerId;
	
	private Date from;
	
	private Date to;
	
}
