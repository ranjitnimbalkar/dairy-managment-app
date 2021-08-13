package com.dairy.managment.app.rest;

import java.util.List;

import com.dairy.managment.app.entity.MilkEntry;

import lombok.Data;

@Data
public class MilkEntriesResponse {

	private List<MilkEntry> entries;
	
	private double totalMilk;
	
	private double rate;
	
	private double total;
	
	private int totalEntries;
}
