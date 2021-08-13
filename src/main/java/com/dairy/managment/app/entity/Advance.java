package com.dairy.managment.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

import com.dairy.managment.app.enums.EntryType;
import com.dairy.managment.app.enums.EntryTypeConverter;

import lombok.Data;

@Entity
@Table(name = "advance")
@Data
public class Advance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;	
	
	private double amount;		
	
	@Convert(converter = EntryTypeConverter.class)
	private EntryType entry_type;
	
	private double closing_balance;
	
	private long customer_id;
	
	@Column
	private Long payment_id;	
	
	private String comment;	
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updated_at;

}
