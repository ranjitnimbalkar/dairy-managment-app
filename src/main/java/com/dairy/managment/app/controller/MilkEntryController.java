package com.dairy.managment.app.controller;

import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.OperationNotSupportedException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dairy.managment.app.entity.MilkEntry;
import com.dairy.managment.app.rest.MilkEntriesResponse;
import com.dairy.managment.app.rest.RestMilkEntry;
import com.dairy.managment.app.service.MilkEntryService;

@RestController
@RequestMapping("/milk/entries/{customerId}")
public class MilkEntryController {
	
	@Autowired
	MilkEntryService milkEntryService;
	
	@PostMapping
	public ResponseEntity<MilkEntry> addMilkEntry(@PathVariable String customerId,
												  @Valid @RequestBody RestMilkEntry entryData) throws OperationNotSupportedException{
		
		entryData.setCustomerId(Long.valueOf(customerId));
		
		System.out.println(entryData);
		
		milkEntryService.addMilkEntry(entryData);
		
		return new ResponseEntity<MilkEntry>(HttpStatus.CREATED);
		
	};
	
	
	@GetMapping("/date/{date}")
	public ResponseEntity<MilkEntriesResponse> getMilkEntry(@PathVariable String customerId,
												  @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
		
		List<MilkEntry> milkEntriesForDate = milkEntryService.getMilkEntriesForDate(Long.valueOf(customerId), date);							
		
		MilkEntriesResponse response = new MilkEntriesResponse();
		
		if(milkEntriesForDate.size() != 0) {
			
			response.setEntries(milkEntriesForDate);
	
			response.setTotal(getSummery(milkEntriesForDate, Constant.TOTAL).getSum());
			response.setRate(getSummery(milkEntriesForDate, Constant.RATE).getAverage());
			response.setTotalMilk(getSummery(milkEntriesForDate, Constant.LITERS).getSum());
			response.setTotalEntries(milkEntriesForDate.size());
		}
		
		return new ResponseEntity<MilkEntriesResponse>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/from/{fromDate}/to/{toDate}")
	public ResponseEntity<MilkEntriesResponse> getMilkEntries(@PathVariable String customerId,
												  		  @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate,
												  		  @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate){
			
        List<MilkEntry> milkEntriesForDate = milkEntryService.getMilkEntriesForDateRange(Long.valueOf(customerId), fromDate, toDate);							
		
		MilkEntriesResponse response = new MilkEntriesResponse();
		
		if(milkEntriesForDate.size() != 0) {
			
			response.setEntries(milkEntriesForDate);
	
			response.setTotal(getSummery(milkEntriesForDate, Constant.TOTAL).getSum());
			response.setRate(getSummery(milkEntriesForDate, Constant.RATE).getAverage());
			response.setTotalMilk(getSummery(milkEntriesForDate, Constant.LITERS).getSum());
			response.setTotalEntries(milkEntriesForDate.size());
		}
		
		return new ResponseEntity<MilkEntriesResponse>(response, HttpStatus.OK);		
		
	}
	
	private DoubleSummaryStatistics getSummery(List<MilkEntry> milkEntries, String summeryFiled) {
		
		DoubleSummaryStatistics summery = new DoubleSummaryStatistics();
		
		if(summeryFiled == Constant.TOTAL)
			summery = milkEntries.stream().collect(Collectors.summarizingDouble(MilkEntry::getTotal));
		
		if(summeryFiled == Constant.RATE)
			summery = milkEntries.stream().collect(Collectors.summarizingDouble(MilkEntry::getRate));
		
		if(summeryFiled == Constant.LITERS)
			summery = milkEntries.stream().collect(Collectors.summarizingDouble(MilkEntry::getLiters));
		
		return summery;
		
	}
	
}
