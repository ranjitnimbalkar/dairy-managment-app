package com.dairy.managment.app.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "customer")
public @Data class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	private  String name;	
	
	@NotEmpty
	@Pattern(regexp = "^\\d{10}$")
	private String mobile;	
	
	private double food_balance;
	
	private double advance_balance;	
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private  Date updated_at;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "address_id")
	private  Address address;
	
}
