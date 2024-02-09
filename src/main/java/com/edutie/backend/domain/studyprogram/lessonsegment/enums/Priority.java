package com.edutie.backend.domain.studyprogram.lessonsegment.enums;

import com.edutie.backend.domain.common.enums.AbstractEnumConverter;
import com.edutie.backend.domain.common.enums.PersistableEnum;
import lombok.Getter;

@Getter
public enum Priority implements PersistableEnum<String> {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");
    private final String code;
    Priority(String code)
    {
        this.code = code;
    }
    public static class Converter extends AbstractEnumConverter<Priority, String> {
        public Converter() {
            super(Priority.class);
        }
    }
}

