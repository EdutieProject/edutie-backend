package com.edutie.backend.domain.education.educator.enums;

import com.edutie.backend.domain.common.enums.PersistableEnum;
import com.edutie.backend.infrastucture.persistence.config.AbstractEnumConverter;
import lombok.*;

/**
 * Enum describing educator type.
 *
 * @implNote Ordinal matters as the higher the ordinal the highest permission level.
 */
@Getter
public enum EducatorType implements PersistableEnum<String> {
	CONTRIBUTOR("Contributor"), TUTOR("Tutor"), PEDAGOGUE("Pedagogue"), ADMINISTRATOR("Administrator");

	final String code;

	EducatorType(String code) {
		this.code = code;
	}

	public static class Converter extends AbstractEnumConverter<EducatorType, String> {
		public Converter() {
			super(EducatorType.class);
		}

	}
}
