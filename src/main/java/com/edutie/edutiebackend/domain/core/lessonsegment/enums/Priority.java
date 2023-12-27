package com.edutie.edutiebackend.domain.core.lessonsegment.enums;

import lombok.Getter;

@Getter
public enum Priority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");
    private final String code;
    Priority(String code)
    {
        this.code = code;
    }
}
