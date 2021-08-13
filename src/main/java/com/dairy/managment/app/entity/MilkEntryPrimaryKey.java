package com.dairy.managment.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dairy.managment.app.enums.MilkTime;
import com.dairy.managment.app.enums.MilkTimeConverter;

import lombok.Data;

@Data
public class MilkEntryPrimaryKey implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public MilkEntryPrimaryKey() {
		super();
	}

	@Column(name = "customer_id")
	private long customerId;

	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Convert(converter = MilkTimeConverter.class)
	private MilkTime milk_time;	

}
