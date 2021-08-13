package com.dairy.managment.app.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MilkTimeConverter implements AttributeConverter<MilkTime, Character>{

	@Override
	public Character convertToDatabaseColumn(MilkTime milkTime) {
		if(milkTime == null)
			return null;
		return milkTime.getTime();
	}

	@Override
	public MilkTime convertToEntityAttribute(Character value) {
		if(value == null)
			return null;
		return MilkTime.fromTime(value);
	}

}
