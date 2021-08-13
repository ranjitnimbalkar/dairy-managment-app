package com.dairy.managment.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

import com.dairy.managment.app.enums.MilkTime;
import com.dairy.managment.app.enums.MilkTimeConverter;

@Entity
@Table(name = "milk_entry")
@IdClass(MilkEntryPrimaryKey.class)
public class MilkEntry {
	
	@Id
	@Column(name = "customer_id")
	private long customerId;

	@Id
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Id
	@Convert(converter = MilkTimeConverter.class)
	private MilkTime milk_time;	

	private long animal_id;
	
	private double liters;
	
	private double snf;
	
	private double fat;
	
	private double degree;
	
	private double rate;
	
	private double total;	
	
	@Column(nullable = true)
	private Long payment_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updated_at;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public MilkTime getMilk_time() {
		return milk_time;
	}

	public void setMilk_time(MilkTime milk_time) {
		this.milk_time = milk_time;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getAnimal_id() {
		return animal_id;
	}

	public void setAnimal_id(long animal_id) {
		this.animal_id = animal_id;
	}

	public double getLiters() {
		return liters;
	}

	public void setLiters(double liters) {
		this.liters = liters;
	}

	public double getSnf() {
		return snf;
	}

	public void setSnf(double snf) {
		this.snf = snf;
	}

	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	public double getDegree() {
		return degree;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Long getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Long payment_id) {
		if(payment_id != null)
			this.payment_id = payment_id;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	} 		
	
}
