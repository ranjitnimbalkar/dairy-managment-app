package com.dairy.managment.app.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EntryType {

	DEBIT('D'),
	CREDIT('C');
	
	private final char type;

	EntryType(char t) {
		this.type = t;
	}
	
	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static EntryType fromType(char type) {
		if(type == 'C' || type == 'c') {
			return CREDIT;
		}
		if(type == 'D' || type == 'd') {
			return DEBIT;
		}
		throw new UnsupportedOperationException("Entry type Can't be : "+ type + " Supported Values [D, C]");
		
	}
	
	public char getType() {
		return type;
	}
	
}
