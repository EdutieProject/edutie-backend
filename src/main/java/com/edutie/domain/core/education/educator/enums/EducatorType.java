package com.edutie.domain.core.education.educator.enums;

import com.edutie.domain.core.common.enums.CodedEnum;
import com.edutie.infrastructure.persistence.config.AbstractEnumConverter;
import lombok.Getter;

/**
 * Enum describing educator type.
 *
 * @implNote Ordinal matters as the higher the ordinal the highest permission level.
 */
@Getter
public enum EducatorType implements CodedEnum<String> {
    COMMUNITY("Community"),
    VERIFIED("Verified"),
    ADMINISTRATOR("Administrator");

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
