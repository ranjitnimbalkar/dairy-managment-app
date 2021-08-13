package com.dairy.managment.app.entity.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dairy.managment.app.entity.MilkEntry;
import com.dairy.managment.app.entity.MilkEntryPrimaryKey;

public interface MilkEntryRepository extends JpaRepository<MilkEntry, MilkEntryPrimaryKey>{
	
	public  List<MilkEntry> findByCustomerIdAndDate(long customerId, Date date);
	
	public  List<MilkEntry> findByCustomerIdAndDateBetween(long customerId, Date fromDate, Date toDate);
}
