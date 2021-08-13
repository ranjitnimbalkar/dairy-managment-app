package com.dairy.managment.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "payment")
@Data
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	
	@Column(name = "customer_id")
	private long customerId;
	
	@Temporal(TemporalType.DATE)
	private Date from_date;
	
	@Temporal(TemporalType.DATE)
	private Date to_date;
	
	private double total_milk;
	
	private double amount;
	
	private double food_deduction;
	
	private double advance_deduction;
	
	private double total_deduction;
	
	private double pay_amount;
	
	private String comment;	
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updated_at;

}
