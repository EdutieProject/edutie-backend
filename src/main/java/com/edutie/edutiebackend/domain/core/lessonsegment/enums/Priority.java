package com.edutie.edutiebackend.domain.core.lessonsegment.enums;

import com.edutie.edutiebackend.domain.core.common.enums.AbstractEnumConverter;
import com.edutie.edutiebackend.domain.core.common.enums.PersistableEnum;
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

