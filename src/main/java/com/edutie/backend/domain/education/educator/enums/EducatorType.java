package com.edutie.backend.domain.education.educator.enums;

import com.edutie.backend.domain.common.enums.AbstractEnumConverter;
import com.edutie.backend.domain.common.enums.PersistableEnum;
import lombok.Getter;

@Getter
public enum EducatorType implements PersistableEnum<String> {
    CONTRIBUTOR("Contributor"),
    TUTOR("Tutor"),
    PEDAGOGUE("Pedagogue");

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
