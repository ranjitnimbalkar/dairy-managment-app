package com.dairy.managment.app.enums;

import javax.persistence.AttributeConverter;

public class EntryTypeConverter implements AttributeConverter<EntryType, Character>{

	@Override
	public Character convertToDatabaseColumn(EntryType attribute) {
		if(attribute == null) {
			return null;
		}
		return attribute.getType();
	}

	@Override
	public EntryType convertToEntityAttribute(Character dbData) {
		if(dbData == null)
			return null;
		return EntryType.fromType(dbData);
	}

}
