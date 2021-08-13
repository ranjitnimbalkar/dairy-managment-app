package com.dairy.managment.app.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MilkTime {
	
	MORNING('M'),
	EVENING('E');
	
	private final char time;

	MilkTime(char t) {
		this.time = t;
	}
	
	@JsonCreator
	public static MilkTime fromTime(char t) {
		if(t == 'M' || t == 'm')
			return MORNING;
		if(t == 'E' || t == 'e')
			return EVENING;		
		throw new UnsupportedOperationException("The time "+ t + " Not Supported!!");
	}

	public char getTime() {
	    return time;
	}	
	
}
