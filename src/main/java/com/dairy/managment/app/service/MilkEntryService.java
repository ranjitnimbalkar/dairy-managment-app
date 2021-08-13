package com.dairy.managment.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.naming.OperationNotSupportedException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairy.managment.app.entity.MilkEntry;
import com.dairy.managment.app.entity.MilkEntryPrimaryKey;
import com.dairy.managment.app.entity.repository.MilkEntryRepository;
import com.dairy.managment.app.rest.RestMilkEntry;

@Service
public class MilkEntryService {

	@Autowired
	MilkEntryRepository milkEntryRepo;
	
	public MilkEntry addMilkEntry(RestMilkEntry inputEntry) throws OperationNotSupportedException {
		
		MilkEntry milkEntry = new MilkEntry();
		
		BeanUtils.copyProperties(inputEntry, milkEntry);
		
		milkEntry.setTotal(inputEntry.getRate() * inputEntry.getLiters());
		
		MilkEntryPrimaryKey key = new MilkEntryPrimaryKey();
		BeanUtils.copyProperties(milkEntry, key);
		
		Optional<MilkEntry> existingMilkEntry = milkEntryRepo.findById(key);
		
		if(existingMilkEntry.isPresent()) {
			throw new OperationNotSupportedException("milk entry already present.");
		}
		
		return milkEntryRepo.save(milkEntry);
	}
	
	public List<MilkEntry> getMilkEntriesForDate(Long customerId, Date date) {		
		return milkEntryRepo.findByCustomerIdAndDate(customerId, date);
	}
	
	public List<MilkEntry> getMilkEntriesForDateRange(Long customerId, Date fromDate, Date toDate) {		
		return milkEntryRepo.findByCustomerIdAndDateBetween(customerId, fromDate, toDate);
	}
	
}
